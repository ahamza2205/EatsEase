package com.example.eatsease.search.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.home.model.response.CategoriesResponse;
import com.example.eatsease.home.model.response.CategoryResponse;

public class CategorySearchItemsAdapter extends RecyclerView.Adapter<CategorySearchItemsAdapter.SearchAdapterViewHolder> {

    private CategoriesResponse categoryList;
    private Context context;

    // Constructor
    public CategorySearchItemsAdapter(CategoriesResponse categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    // Setter for category list
    public void setCategoryList(CategoriesResponse categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();  // This will refresh the RecyclerView with the new data
    }

    @NonNull
    @Override
    public SearchAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_items, parent, false);
        return new SearchAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapterViewHolder holder, int position) {
        if (categoryList != null && categoryList.categories != null && !categoryList.categories.isEmpty()) {
            CategoryResponse category = categoryList.categories.get(position);

            // Bind data to views
            holder.categoryNameTextView.setText(category.strCategory);
            Glide.with(context)
                    .load(category.strCategoryThumb)
                    .into(holder.categoryThumbnailImageView);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList != null && categoryList.categories != null ? categoryList.categories.size() : 0;
    }

    public static class SearchAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView categoryNameTextView;
        ImageView categoryThumbnailImageView;

        public SearchAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameTextView = itemView.findViewById(R.id.searchcategoryName);
            categoryThumbnailImageView = itemView.findViewById(R.id.searchcategoryImage);
        }
    }
}
