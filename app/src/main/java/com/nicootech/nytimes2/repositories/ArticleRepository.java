package com.nicootech.nytimes2.repositories;

import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.request.ArticleApiClient;
import java.util.List;
import androidx.lifecycle.LiveData;

public class ArticleRepository {

    private static ArticleRepository instance;
    private ArticleApiClient mArticleApiClient;
    private String mQuery;
    private int mPageNumber;

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

    public void searchArticlesApi(String query, int pageNumber){
        if(pageNumber == 0){
            pageNumber = 1;
        }
        mQuery = query;
        mPageNumber = pageNumber;
        mArticleApiClient.searchArticlesApi(query,pageNumber);
    }
    public void searchNextPage(){
        searchArticlesApi(mQuery, mPageNumber+1);
    }
    public void cancelRequest(){
        mArticleApiClient.cancelRequest();
    }
}
