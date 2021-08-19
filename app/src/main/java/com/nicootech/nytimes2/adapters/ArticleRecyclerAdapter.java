package com.nicootech.nytimes2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicootech.nytimes2.R;
import com.nicootech.nytimes2.models.Docs;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ARTICLE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private List<Docs> mDocs;
    private OnArticleListener mOnArticleListener;

    public ArticleRecyclerAdapter(OnArticleListener mOnArticleListener) {
        this.mOnArticleListener = mOnArticleListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        switch (i){
            case ARTICLE_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_article_list_item, viewGroup,false);
                return new ArticleViewHolder(view,mOnArticleListener);

            }
            case LOADING_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_list_item, viewGroup,false);
                return new LoadingViewHolder(view);

            }
            default:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_article_list_item, viewGroup,false);
                return new ArticleViewHolder(view,mOnArticleListener);

            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int itemViewType = getItemViewType(i);
        if (itemViewType == ARTICLE_TYPE) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(viewHolder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mDocs.get(i).getMultimedia().get(1).getUrl())
                    .into(((ArticleViewHolder) viewHolder).image);

            ((ArticleViewHolder) viewHolder).headline.setText(mDocs.get(i).getHeadline().getMain());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(mDocs.get(position).getSnippet().equals("LOADING...")){
            return LOADING_TYPE;
        }
        else{
            return ARTICLE_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if(mDocs!= null){
            return mDocs.size();
        }
        return 0;
    }
    public void displayLoading(){
        if(!isLoading()){
            Docs docs = new Docs();
            docs.setSnippet("LOADING...");
            List<Docs> loadingList = new ArrayList<>();
            loadingList.add(docs);
            mDocs = loadingList;
            notifyDataSetChanged();
        }
    }
    private boolean isLoading(){
        if(mDocs != null){
            if(mDocs.size()>0){
                if(mDocs.get(mDocs.size()-1).getSnippet().equals("LOADING...")){
                    return true;
                }
            }
        }
        return false;
    }

    public void setArticles(List<Docs> docs){
        mDocs = docs;
        notifyDataSetChanged();

    }
}
