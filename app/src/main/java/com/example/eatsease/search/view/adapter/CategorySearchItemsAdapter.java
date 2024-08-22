package com.example.eatsease.search.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.home.view.fragment.HomeFragmentDirections;
import com.example.eatsease.home.view.fragment.adapter.categories.CategoryAdapter;
import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.CategoryResponse;
import com.example.eatsease.search.view.SearchFragmentDirections;

public class CategorySearchItemsAdapter extends RecyclerView.Adapter<CategorySearchItemsAdapter.SearchAdapterViewHolder> {

    private CategoriesResponse categoryList;
    private Context context;
    private CategoryAdapter.OnCategoryClickListener onCategoryClickListener;

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
        // Declare category variable outside the if block
        CategoryResponse category;

        if (categoryList != null && categoryList.categories != null && !categoryList.categories.isEmpty()) {
            // Assign the category within the if block
            category = categoryList.categories.get(position);

            // Bind data to views
            holder.categoryNameTextView.setText(category.strCategory);
            Glide.with(context)
                    .load(category.strCategoryThumb)
                    .into(holder.categoryThumbnailImageView);
        } else {
            category = null;
        }
        // Handle category click to navigate to SearchItemFragment

        // Check if category is not null before setting the click listener
        if (category != null) {
            holder.itemView.setOnClickListener(v -> {
                SearchFragmentDirections.ActionSearchFragment2ToSearchItemFragment action =
                        SearchFragmentDirections.actionSearchFragment2ToSearchItemFragment( null,category.getStrCategory());
                Navigation.findNavController(v).navigate(action);
            });
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
