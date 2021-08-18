package com.nicootech.nytimes2;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.util.Testing;
import com.nicootech.nytimes2.viewmodels.ArticleListViewModel;
import java.util.List;

public class ArticleListActivity extends BaseActivity {
    private static final String TAG = "ArticleListActivity";

    private ArticleListViewModel mArticleListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        mArticleListViewModel = new ViewModelProvider(this).get(ArticleListViewModel.class);

        subscribeObservers();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }

    private void subscribeObservers(){
        mArticleListViewModel.getDocs().observe(this, new Observer<List<Docs>>() {
            @Override
            public void onChanged(List<Docs> docs) {
               if(docs != null){
                   Testing.printArticles("Article test",docs);
               }

            }
        });
    }

//    private void searchArticlesApi(String query, int pageNumber){
//        mArticleListViewModel.searchArticlesApi(query,pageNumber);
//    }

    private void testRetrofitRequest(){
        mArticleListViewModel.searchArticlesApi("afghanistan",1);
    }
}