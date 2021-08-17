package com.nicootech.nytimes2.viewmodels;

import com.nicootech.nytimes2.models.Docs;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArticleListViewModel extends ViewModel {

    private MutableLiveData<List<Docs>> mDocs = new MutableLiveData<>();

    public ArticleListViewModel() {
    }
    public LiveData<List<Docs>> getDocs(){
        return mDocs;
    }
}
