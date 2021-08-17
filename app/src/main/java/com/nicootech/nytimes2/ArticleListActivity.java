package com.nicootech.nytimes2;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.request.NYTApi;
import com.nicootech.nytimes2.request.ServiceGenerator;
import com.nicootech.nytimes2.request.responses.ArticleSearchResponse;
import com.nicootech.nytimes2.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends BaseActivity {
    private static final String TAG = "ArticleListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }

    private void testRetrofitRequest(){
        NYTApi nytApi = ServiceGenerator.getNytApi();

        Call<ArticleSearchResponse> responseCall = nytApi
                .searchArticle(
                        Constants.API_KEY,
                        "afghanistan",
                        "1"
                );

        responseCall.enqueue(new Callback<ArticleSearchResponse>() {
            @Override
            public void onResponse(Call<ArticleSearchResponse> call, Response<ArticleSearchResponse> response) {
                Log.d(TAG, "onResponse: server response"+response.toString());
                if(response.code() == 200){
                    Log.d(TAG, "onResponse: "+response.body().toString());
                    List<Docs>docs = new ArrayList<>(response.body().getResponse().getDocs());
                    for(Docs doc : docs){
                        Log.d(TAG, "onResponse: "+doc.getHeadline().getMain());
                    }
                }
                else{
                    try {
                        Log.d(TAG, "onResponse: "+response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();

                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleSearchResponse> call, Throwable t) {

            }
        });
    }
}