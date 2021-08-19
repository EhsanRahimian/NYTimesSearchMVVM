package com.nicootech.nytimes2.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nicootech.nytimes2.R;
import com.nicootech.nytimes2.models.Docs;
import com.nicootech.nytimes2.util.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ARTICLE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;
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
            case CATEGORY_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category_list_item, viewGroup,false);
                return new CategoryViewHolder(view,mOnArticleListener);

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
        else if (itemViewType == CATEGORY_TYPE) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Uri path = Uri.parse("android.resource://com.nicootech.nytimes2/drawable" +
                    mDocs.get(i).getMultimedia().get(1).getUrl());
            Glide.with(viewHolder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(path)
                    .into(((CategoryViewHolder) viewHolder).categoryImage);

            ((CategoryViewHolder) viewHolder).categoryTitle.setText(mDocs.get(i).getHeadline().getMain());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(mDocs.get(position)== null){//I want to use the marker to notify the getItemViewType()
            //to return the CATEGORY_TYPE... so I used mDocs.get(position)== null !
            return CATEGORY_TYPE;
        }

        else if(mDocs.get(position).getHeadline().getMain().equals("LOADING...")){
            return LOADING_TYPE;
        }

        else if(position == mDocs.size()-1
            && position !=0
            && !mDocs.get(position).getHeadline().equals("EXHAUSTED...")){
            return LOADING_TYPE;
        }
        else{
            return ARTICLE_TYPE;
        }
    }

    public void displaySearchCategories(){
        List<Docs> categories = new ArrayList<>();
        for(int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++){
            Docs docs = new Docs();
            docs.getHeadline().setMain(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
            docs.getMultimedia().get(1).setUrl(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
            //
            mDocs.get(i).equals(null);
            //
            categories.add(docs);

        }
        mDocs = categories;
        notifyDataSetChanged();

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
