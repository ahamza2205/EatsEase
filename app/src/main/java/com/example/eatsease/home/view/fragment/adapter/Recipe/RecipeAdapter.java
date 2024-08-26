package com.example.eatsease.home.view.fragment.adapter.Recipe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.eatsease.login_signup.authentication.activity.login.LogIn;
import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPerferencesImp;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.response.Meal;
import com.google.firebase.auth.FirebaseUser;


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
            FirebaseUser currentUser = FirebaseAuthManager.getInstance().getCurrentUser();
            if (currentUser == null || currentUser.isAnonymous()) {
                // User is not logged in or is using anonymous authentication, show the dialog
                showCustomDialog();
            } else {
                // Create a FavoriteMeal object based on the current Meal
                FavoriteMeal favoriteMeal = new FavoriteMeal();
                favoriteMeal.setMealId(recipe.getMealId());
                favoriteMeal.setMealName(recipe.getMealName());
                favoriteMeal.setThumbnail(recipe.getMealThumbnail());

                // Insert the favorite meal using HomePresenter
                onRecipeClickListener.onFavoriteClick(favoriteMeal);
                Toast.makeText(context, "Meal Added to favorites", Toast.LENGTH_SHORT).show();
            }
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

    private void showCustomDialog() {
        // Inflate the custom dialog layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView2 = inflater.inflate(R.layout.dialog_login, null);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        builder.setView(dialogView2);

        // Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        // Find and set up the views inside the dialog
        ImageView dialogImage = dialogView2.findViewById(R.id.dialogImage);
        TextView dialogTitle = dialogView2.findViewById(R.id.dialogTitle);
        TextView dialogMessage = dialogView2.findViewById(R.id.dialogMessage);
        Button dialogButton = dialogView2.findViewById(R.id.dialogButton);

        // Set content for the dialog views
        dialogImage.setImageResource(R.drawable.login); // Set your image resource here
        dialogTitle.setText("Not Logged In");
        dialogMessage.setText("Please log in to save your favorite meals.");

        // Set a click listener for the button
        dialogButton.setOnClickListener(v -> {
            // Dismiss the dialog
            alertDialog.dismiss();

            // Log out the user if needed (assumes you have a FirebaseAuthManager)
            FirebaseAuthManager authManager = FirebaseAuthManager.getInstance();
            if (authManager != null) {
                authManager.signOut();
            }

            // Optionally remove user details from SharedPreferences if applicable
            SharedPerferencesImp sharedPrefRespiratory = SharedPerferencesImp.getInstance(context);
             sharedPrefRespiratory.removePreferences();

            // Navigate to the login activity
            Intent intent = new Intent(context, LogIn.class);
            context.startActivity(intent);
        });

        // Show the dialog
        alertDialog.show();
    }
}

