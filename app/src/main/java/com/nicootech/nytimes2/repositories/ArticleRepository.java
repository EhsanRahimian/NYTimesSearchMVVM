package com.nicootech.nytimes2.repositories;

import com.nicootech.nytimes2.models.Docs;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ArticleRepository {
    private static ArticleRepository instance;
    private MutableLiveData<List<Docs>> mDocs;

    public static ArticleRepository getInstance(){
        if(instance == null){
            instance = new ArticleRepository();
        }
        return instance;
    }
    public ArticleRepository() {
        mDocs = new MutableLiveData<>();
    }
    public LiveData<List<Docs>> getDocs(){
        return mDocs;
    }
}
