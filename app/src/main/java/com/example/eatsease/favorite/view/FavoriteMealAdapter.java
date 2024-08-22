//package com.example.eatsease.favorite.view;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.eatsease.R;
//import com.example.eatsease.model.database.FavoriteMeal;
//
//import java.util.List;
//
//public class FavoriteMealAdapter extends RecyclerView.Adapter<FavoriteMealAdapter.FavoriteMealViewHolder> {
//    private List<FavoriteMeal> favoriteMeals;
//    private final Context context;
//    private final OnFavoriteClickListener listener;
//
//    public interface OnFavoriteClickListener {
//        void onFavoriteClick(FavoriteMeal meal, ImageView favoriteIcon);
//    }
//
//    public FavoriteMealAdapter(List<FavoriteMeal> favoriteMeals, Context context, OnFavoriteClickListener listener) {
//        this.favoriteMeals = favoriteMeals;
//        this.context = context;
//        this.listener = listener;
//    }
//
//    public void setFavoriteMeals(List<FavoriteMeal> favoriteMeals) {
//        this.favoriteMeals = favoriteMeals;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public FavoriteMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.item_favorite_meal, parent, false); // Update with correct layout
//        return new FavoriteMealViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FavoriteMealViewHolder holder, int position) {
//        FavoriteMeal meal = favoriteMeals.get(position);
//
//        holder.favRecipeName.setText(meal.getMealName());
//        Glide.with(context).load(meal.getPhotoUrl()).into(holder.favRecipeImage);
//
//        holder.favoriteButton.setOnClickListener(v -> listener.onFavoriteClick(meal, holder.favoriteButton));
//    }
//
//    @Override
//    public int getItemCount() {
//        return favoriteMeals != null ? favoriteMeals.size() : 0;
//    }
//
//    public static class FavoriteMealViewHolder extends RecyclerView.ViewHolder {
//        ImageView favRecipeImage;
//        TextView favRecipeName;
//        ImageButton favoriteButton;
//
//        public FavoriteMealViewHolder(@NonNull View itemView) {
//            super(itemView);
//            favRecipeImage = itemView.findViewById(R.id.favrecipe_image);
//            favRecipeName = itemView.findViewById(R.id.favrecipe_name);
//            favoriteButton = itemView.findViewById(R.id.favorite_button);
//        }
//    }
//}
