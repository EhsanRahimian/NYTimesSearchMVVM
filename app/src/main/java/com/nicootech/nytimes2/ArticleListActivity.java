package com.nicootech.nytimes2;

import androidx.appcompat.widget.SearchView;
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
import com.nicootech.nytimes2.util.VerticalSpacingItemDecorator;
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
        //testRetrofitRequest();
        initSearchView();
        if(!mArticleListViewModel.isViewingArticles()){
            displaySearchCategories();
        }

    }
    private void initRecyclerView(){
        mAdapter = new ArticleRecyclerAdapter(this);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setQueryHint("covid...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mAdapter.displayLoading();
                mArticleListViewModel.searchArticlesApi(query, 1);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void subscribeObservers(){
        mArticleListViewModel.getDocs().observe(this, new Observer<List<Docs>>() {
            @Override
            public void onChanged(List<Docs> docs) {
               if(docs != null){
                   if(mArticleListViewModel.isViewingArticles()){
                       Testing.printArticles("Article test",docs);
                       mAdapter.setArticles(docs);
                   }
               }
            }
        });
    }

    private void searchArticlesApi(String query, int pageNumber){
        mArticleListViewModel.searchArticlesApi(query,pageNumber);
    }

//    private void testRetrofitRequest(){
//        searchArticlesApi("afghanistan",1);
//    }

    @Override
    public void onArticleClick(int position) {
    }

    @Override
    public void onCategoryClick(String category) {
        mAdapter.displayLoading();
        mArticleListViewModel.searchArticlesApi(category,1);
    }

    private void displaySearchCategories(){
        mArticleListViewModel.setIsViewingArticles(false);
        mAdapter.displaySearchCategories();
    }

    @Override
    public void onBackPressed() {

        if(mArticleListViewModel.onBackPressed()){
            super.onBackPressed();
        }
        else{
            displaySearchCategories();
        }
    }
}