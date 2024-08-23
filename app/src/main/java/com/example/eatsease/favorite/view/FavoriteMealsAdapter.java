package com.example.eatsease.favorite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.favoritemeal> {
    List<FavoriteMeal> FavoriteMeal;
    Context context;

    public FavoriteMealsAdapter(List<FavoriteMeal> FavoriteMeal, Context context) {
        this.FavoriteMeal = FavoriteMeal;
        this.context = context;
    }

    public void setFavoriteMeal(List<FavoriteMeal> FavoriteMeal) {
        this.FavoriteMeal = FavoriteMeal;
    }

    @NonNull
    @Override
    public favoritemeal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new favoritemeal(inflater.inflate(R.layout., parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull favoritemeal holder, int position) {

    }

    public void updatedata(List<FavoriteMeal> FavoriteMeal) {
        this.FavoriteMeal = FavoriteMeal;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return FavoriteMeal.size();
    }

    public class favoritemeal extends RecyclerView.ViewHolder {

        public favoritemeal(@NonNull View itemView) {
            super(itemView);

        }
    }
}