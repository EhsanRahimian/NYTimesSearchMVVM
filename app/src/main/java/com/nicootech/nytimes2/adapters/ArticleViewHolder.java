package com.nicootech.nytimes2.adapters;
import android.view.View;
import android.widget.TextView;
import com.nicootech.nytimes2.R;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView headline;
    AppCompatImageView image;
    OnArticleListener onArticleListener;

    public ArticleViewHolder(@NonNull View itemView, OnArticleListener onArticleListener) {
        super(itemView);
        this.onArticleListener = onArticleListener;
        headline = itemView.findViewById(R.id.article_headline);
        image = itemView.findViewById(R.id.img_thumbnail);

        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        onArticleListener.onArticleClick(getAbsoluteAdapterPosition());
    }
}
