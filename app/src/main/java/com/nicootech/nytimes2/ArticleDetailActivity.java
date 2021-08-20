package com.nicootech.nytimes2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.nicootech.nytimes2.models.Docs;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class ArticleDetailActivity extends BaseActivity {

    private ScrollView mScrollView;
    private AppCompatImageView mArticleImage;
    private TextView mArticleHeader, mArticleWriter, mArticleParagraph ,mArticleUrl;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_article_detail);
        mArticleHeader = findViewById(R.id.header);
        mArticleWriter = findViewById(R.id.writer);
        mArticleParagraph = findViewById(R.id.main_paragraph);
        mArticleUrl = findViewById(R.id.link_url);
        mScrollView = findViewById(R.id.parent);
        mArticleImage = findViewById(R.id.article_image);

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("docs")){
            Docs docs = getIntent().getParcelableExtra("docs");
        }
    }
}
