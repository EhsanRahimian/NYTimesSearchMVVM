package com.nicootech.nytimes2.request;

import android.util.Log;

import com.nicootech.nytimes2.AppExecutors;
import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.request.responses.ArticleSearchResponse;
import com.nicootech.nytimes2.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

import static com.nicootech.nytimes2.util.Constants.NETWORK_TIMEOUT;

public class ArticleApiClient {

    private static final String TAG = "ArticleApiClient";

    private static ArticleApiClient instance;
    private MutableLiveData<List<Docs>> mDocs;
    private RetrieveArticleRunnable mRetrieveArticleRunnable;

    public static ArticleApiClient getInstance() {
        if (instance == null) {
            instance = new ArticleApiClient();
        }
        return instance;
    }

    public ArticleApiClient() {
        mDocs = new MutableLiveData<>();
    }

    public LiveData<List<Docs>> getDocs(){
        return mDocs;
    }

    public void searchArticlesApi(String query, int pageNumber){
        if(mRetrieveArticleRunnable != null){
            mRetrieveArticleRunnable = null; // because we need to instantiate the new one as seen in below:
        }
        mRetrieveArticleRunnable = new RetrieveArticleRunnable(query, pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveArticleRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know its timed out
                handler.cancel(true);
            }
        },NETWORK_TIMEOUT, TimeUnit.MICROSECONDS);

    }

    private class RetrieveArticleRunnable implements Runnable{

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveArticleRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getArticles(query,pageNumber).execute();
                if(cancelRequest) {
                    return;
                }
                if(response.code() == 200){
                    List<Docs> list = new ArrayList<>(((ArticleSearchResponse)response.body()).getResponse().getDocs());
                    if(pageNumber == 1){
                        mDocs.postValue(list);
                    }
                    else{
                        List<Docs> currentArticle = mDocs.getValue();
                        currentArticle.addAll(list);
                        mDocs.postValue(currentArticle);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.d(TAG, "run: "+error);
                    mDocs.postValue(null); // in future we detect null if find it so we figure
                    // it out the is an error and let the user know
                }
            } catch (IOException e) {
                e.printStackTrace();
                mDocs.postValue(null);
            }


        }
        private Call<ArticleSearchResponse> getArticles(String query, int pageNumber){
            return ServiceGenerator.getNytApi().searchArticle(
                    Constants.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }
        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the request.");
            cancelRequest = true;

        }
    }

}















