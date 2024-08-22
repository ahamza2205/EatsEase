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
import com.example.eatsease.model.network.response.Ingredientt;

import java.util.List;

public class IngredientSearchItemsAdapter extends RecyclerView.Adapter<IngredientSearchItemsAdapter.ingredient> {
    List<Ingredientt> ingredientt;
    Context context;

    public IngredientSearchItemsAdapter(List<Ingredientt> ingredientt, Context context) {
        this.ingredientt = ingredientt;  // Assign the parameter to the class field
        this.context = context;
    }

    public void setIngredientt(List<Ingredientt> Ingredientt) {
        this.ingredientt = Ingredientt;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ingredient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ingredient(inflater.inflate(R.layout.search_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ingredient holder, int position) {
        Ingredientt ingredientt1 = ingredientt.get(position) ;
        holder.ingredientName.setText(ingredientt1.getStrIngredient());
        Glide.with(holder.itemView.getContext())
                .load("https://www.themealdb.com/images/ingredients/" + ingredientt1.getStrIngredient() + "-Small.png")
                .into(holder.ingredientImage);
    }

    public void updatedata(List<Ingredientt> ingredientt) {
        this.ingredientt = ingredientt;  // Assign the new list to the adapter's list
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ingredientt != null ? ingredientt.size() : 0; }

    public class ingredient extends RecyclerView.ViewHolder {
        TextView ingredientName;
        ImageView ingredientImage ;
        public ingredient(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.searchcategoryName);
            ingredientImage = itemView.findViewById(R.id.searchcategoryImage);
        }
    }
}