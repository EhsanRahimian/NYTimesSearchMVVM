package com.nicootech.nytimes2.viewmodels;

import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.repositories.ArticleRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ArticleListViewModel extends ViewModel {

    private boolean mIsViewingArticles;
    private ArticleRepository mArticleRepository;

    public ArticleListViewModel() {
        mArticleRepository = ArticleRepository.getInstance();
    }

    public LiveData<List<Docs>> getDocs(){
        return mArticleRepository.getDocs();
    }

    public void searchArticlesApi(String query, int pageNumber){
        mIsViewingArticles = true;
        mArticleRepository.searchArticlesApi(query,pageNumber);
    }
    public boolean isViewingArticles(){
        return mIsViewingArticles;
    }

    public void setIsViewingArticles(boolean isViewingArticles){
        mIsViewingArticles = isViewingArticles;
    }
    public boolean onBackPressed(){
        if(mIsViewingArticles){
            mIsViewingArticles = false;
            return false;
        }
        return true;
    }

}
