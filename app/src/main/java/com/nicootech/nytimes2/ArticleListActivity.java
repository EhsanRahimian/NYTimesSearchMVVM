package com.nicootech.nytimes2;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.nicootech.nytimes2.adapters.ArticleRecyclerAdapter;
import com.nicootech.nytimes2.adapters.OnArticleListener;
import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.util.Testing;
import com.nicootech.nytimes2.viewmodels.ArticleListViewModel;
import java.util.List;

public class ArticleListActivity extends BaseActivity implements OnArticleListener {
    private static final String TAG = "ArticleListActivity";
    private RecyclerView mRecyclerView;
    private ArticleRecyclerAdapter mAdapter;


    private ArticleListViewModel mArticleListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        mRecyclerView = findViewById(R.id.article_list);


        mArticleListViewModel = new ViewModelProvider(this).get(ArticleListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        testRetrofitRequest();

    }
    private void initRecyclerView(){
        mAdapter = new ArticleRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void subscribeObservers(){
        mArticleListViewModel.getDocs().observe(this, new Observer<List<Docs>>() {
            @Override
            public void onChanged(List<Docs> docs) {
               if(docs != null){
                   Testing.printArticles("Article test",docs);

                   mAdapter.setArticles(docs);
               }

            }
        });
    }

    private void searchArticlesApi(String query, int pageNumber){
        mArticleListViewModel.searchArticlesApi(query,pageNumber);
    }

    private void testRetrofitRequest(){
        searchArticlesApi("afghanistan",1);
    }

    @Override
    public void onArticleClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}