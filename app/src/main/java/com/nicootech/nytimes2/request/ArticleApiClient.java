package com.nicootech.nytimes2.request;

import com.nicootech.nytimes2.AppExecutors;
import com.nicootech.nytimes2.models.Docs;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static com.nicootech.nytimes2.util.Constants.NETWORK_TIMEOUT;

public class ArticleApiClient {

    private static ArticleApiClient instance;
    private MutableLiveData<List<Docs>> mDocs;

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

    public void searchArticlesApi(){
        final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                //retrieve data from rest api
                // mDocs.postValue();
            }
        });
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know its timed out
                handler.cancel(true);
            }
        },NETWORK_TIMEOUT, TimeUnit.MICROSECONDS);

    }

}















