package com.nicootech.nytimes2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicootech.nytimes2.R;
import com.nicootech.nytimes2.models.Docs;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Docs> mDocs;
    private OnArticleListener mOnArticleListener;

    public ArticleRecyclerAdapter(List<Docs> mDocs, OnArticleListener mOnArticleListener) {
        this.mDocs = mDocs;
        this.mOnArticleListener = mOnArticleListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_article_list_item, viewGroup,false);
        return new ArticleViewHolder(view, mOnArticleListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((ArticleViewHolder)viewHolder).headline.setText(mDocs.get(i).getHeadline().getMain());
    }

    @Override
    public int getItemCount() {
        if(mDocs!= null){
            return mDocs.size();
        }
        return 0;
    }

    public void setArticles(List<Docs> docs){
        mDocs = docs;
        notifyDataSetChanged();

    }
}
