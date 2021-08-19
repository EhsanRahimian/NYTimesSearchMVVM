package com.nicootech.nytimes2.adapters;

import android.view.View;
import android.widget.TextView;

import com.nicootech.nytimes2.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView categoryTitle;
    CircleImageView categoryImage;
    OnArticleListener listener;

    public CategoryViewHolder(@NonNull View itemView, OnArticleListener listener) {
        super(itemView);

        this.listener = listener;
        categoryImage = itemView.findViewById(R.id.category_image);
        categoryTitle = itemView.findViewById(R.id.category_title);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onCategoryClick(categoryTitle.getText().toString());

    }
}
