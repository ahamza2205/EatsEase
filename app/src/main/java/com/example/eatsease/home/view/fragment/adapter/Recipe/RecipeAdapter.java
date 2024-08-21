package com.example.eatsease.home.view.fragment.adapter.Recipe;

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
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.home.ui.fragment.HomeFragmentDirections;


import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Meal> recipesList;
    private Context context;

    public RecipeAdapter(List<Meal> recipesList, Context context) {
        this.recipesList = recipesList != null ? recipesList : new ArrayList<>(); // Initialize with empty list if null
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Meal recipe = recipesList.get(position);
        holder.recipeName.setText(recipe.getMealName());
        Glide.with(context)
                .load(recipe.getMealThumbnail())
                .into(holder.recipeImage);

        // Set click listener for each recipe item
        holder.itemView.setOnClickListener(v -> {
            // Handle recipe item click
            HomeFragmentDirections.ActionHomeFragmentToMealDetailFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealDetailFragment(recipe.getMealId());
            Navigation.findNavController(holder.itemView).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public void updateRecipeList(List<Meal> newRecipes) {
        this.recipesList = newRecipes != null ? newRecipes : new ArrayList<>();
        notifyDataSetChanged(); // Refresh RecyclerView with new data
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        ImageView recipeImage;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name);
            recipeImage = itemView.findViewById(R.id.recipe_image);
        }
    }
}
