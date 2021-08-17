package com.nicootech.nytimes2.repositories;

import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.request.ArticleApiClient;
import java.util.List;
import androidx.lifecycle.LiveData;

public class ArticleRepository {
    private static ArticleRepository instance;
    private ArticleApiClient mArticleApiClient;

    public static ArticleRepository getInstance(){
        if(instance == null){
            instance = new ArticleRepository();
        }
        return instance;
    }
    public ArticleRepository() {
        mArticleApiClient = ArticleApiClient.getInstance();
    }
    public LiveData<List<Docs>> getDocs(){
        return mArticleApiClient.getDocs();
    }
}
