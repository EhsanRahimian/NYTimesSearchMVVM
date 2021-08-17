package com.nicootech.nytimes2.request;

import com.nicootech.nytimes2.models.Docs;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

}
