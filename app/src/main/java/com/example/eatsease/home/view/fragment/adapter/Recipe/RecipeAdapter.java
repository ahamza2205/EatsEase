package com.example.eatsease.home.view.fragment.adapter.Recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.home.presenter.HomePresenter;
import com.example.eatsease.home.view.fragment.HomeFragmentDirections;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.response.Meal;


import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Meal> recipesList;
    private Context context;
     private HomePresenter homePresenter;
    private OnRecipeClickListener onRecipeClickListener;


    public RecipeAdapter(List<Meal> recipesList, Context context, OnRecipeClickListener onRecipeClickListener) {
        this.recipesList = recipesList != null ? recipesList : new ArrayList<>(); // Initialize with empty list if null
        this.context = context;
        this.onRecipeClickListener = onRecipeClickListener;
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
            if (onRecipeClickListener != null) {
                onRecipeClickListener.onRecipeClick(recipe);
            }
            // Handle recipe item click
        });

        holder.favoriteButton.setOnClickListener(v -> {
            // Create a FavoriteMeal object based on the current Meal
            FavoriteMeal favoriteMeal = new FavoriteMeal();
            favoriteMeal.setMealId(recipe.getMealId());
            favoriteMeal.setMealName(recipe.getMealName());
            favoriteMeal.setThumbnail(recipe.getMealThumbnail());

            // Insert the favorite meal using HomePresenter
            onRecipeClickListener.onFavoriteClick(favoriteMeal);
            Toast.makeText(context, "Meal Added to favorites", Toast.LENGTH_SHORT).show();
        });

    }


    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public void  updateRecipeList(List<Meal> newRecipes) {
            recipesList.clear();
            recipesList.addAll(newRecipes);
            notifyDataSetChanged();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        ImageView recipeImage;
        ImageButton favoriteButton;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            favoriteButton = itemView.findViewById(R.id.home_recipe_favorite_button);

        }
    }
    public interface OnRecipeClickListener {
        void onRecipeClick(Meal meal);
        void onFavoriteClick(FavoriteMeal meal);
    }
}

