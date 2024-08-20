package com.example.eatsease.home.adapter.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.home.model.response.CategoriesResponse;
import com.example.eatsease.home.model.response.CategoryResponse;

import io.reactivex.rxjava3.annotations.NonNull;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    public CategoriesResponse categoryList;
    private final Context context;
    private final OnCategoryClickListener onCategoryClickListener;

    public CategoryAdapter(CategoriesResponse categoryList, Context context, OnCategoryClickListener onCategoryClickListener) {
        this.categoryList = categoryList;
        this.context = context;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryResponse category = categoryList.categories.get(position);
        holder.categoryName.setText(categoryList.categories.get(position).strCategory);
        Glide.with(context)
                .load(category.strCategoryThumb)
                .into(holder.categoryImage);

        // Handle category click
        holder.itemView.setOnClickListener(v -> onCategoryClickListener.onCategoryClick(category));
    }

    @Override
    public int getItemCount() {
        if (categoryList.categories == null) {
            return 0;
        }
        return categoryList.categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_image);
            categoryName = itemView.findViewById(R.id.category_name);
        }
    }

    // Interface for handling category clicks
    public interface OnCategoryClickListener {
        void onCategoryClick(CategoryResponse category);
    }
}