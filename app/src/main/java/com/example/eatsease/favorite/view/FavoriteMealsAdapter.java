package com.example.eatsease.favorite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.favorite.presenter.FavoriteMealPresenter;
import com.example.eatsease.model.database.FavoriteMeal;
import java.util.List;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.FavoriteMealViewHolder> {

    private List<FavoriteMeal> favoriteMeals;
    private Context context;
    private FavoriteMealPresenter favoriteMealPresenter ;

    public FavoriteMealsAdapter(List<FavoriteMeal> favoriteMeals, Context context, FavoriteMealPresenter favoriteMealPresenter) {
        this.favoriteMeals = favoriteMeals;
        this.context = context;
        this.favoriteMealPresenter = favoriteMealPresenter;
    }

    public void setFavoriteMeal(List<FavoriteMeal> favoriteMeals) {
        this.favoriteMeals = favoriteMeals;
    }

    @NonNull
    @Override
    public FavoriteMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favoritemeal, parent, false);
        return new FavoriteMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMealViewHolder holder, int position) {
        FavoriteMeal favoriteMeal = favoriteMeals.get(position);
        holder.favRecipeName.setText(favoriteMeal.getMealName());

        // Load image using Glide or any other image loading library
        Glide.with(context)
                .load(favoriteMeal.getThumbnail())
                .into(holder.favRecipeImage);


        holder.favRecipeFavoriteButton.setOnClickListener(v -> {
            favoriteMealPresenter.deleteFavoriteMeal(favoriteMeal);
            favoriteMeals.remove(position); // Remove the item from the list
            notifyItemRemoved(position); // Notify the adapter that the item is removed
            notifyItemRangeChanged(position, favoriteMeals.size()); // Notify the adapter to update the remaining items
            Toast.makeText(context, "Meal Deleted From Favorites", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return favoriteMeals.size();
    }

    public static class FavoriteMealViewHolder extends RecyclerView.ViewHolder {

        ImageView favRecipeImage;
        TextView favRecipeName;
        ImageButton favRecipeFavoriteButton;

        public FavoriteMealViewHolder(@NonNull View itemView) {
            super(itemView);
            favRecipeImage = itemView.findViewById(R.id.favrecipe_image);
            favRecipeName = itemView.findViewById(R.id.favrecipe_name);
            favRecipeFavoriteButton = itemView.findViewById(R.id.fav_recipe_favorite_button);
        }
    }

}
