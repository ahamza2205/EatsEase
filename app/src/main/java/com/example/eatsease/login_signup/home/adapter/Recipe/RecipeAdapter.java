package com.example.eatsease.login_signup.home.adapter.Recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.login_signup.home.model.response.Meal;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Meal> recipesList;
    private Context context;

    public RecipeAdapter(List<Meal> recipesList, Context context) {
        this.recipesList = recipesList;
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
    }

    @Override
    public int getItemCount() {
        if (recipesList == null){
            return  0;
        }
        return recipesList.size();
    }

    public void updateRecipeList(List<Meal> newRecipes) {
        this.recipesList = newRecipes;
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

