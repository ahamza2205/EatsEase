package com.example.eatsease.plan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.plan.model.MealPlan;

import java.util.List;

public class MealPlanAdapter extends RecyclerView.Adapter<MealPlanAdapter.MealPlanViewHolder> {

    private List<MealPlan> mealPlanList;
    private Context context;
    private OnMealPlanListener listener;

    // Interface to handle click events
    public interface OnMealPlanListener {
        void onDeleteClick(MealPlan mealPlan);
    }

    public MealPlanAdapter(List<MealPlan> mealPlanList, Context context, OnMealPlanListener listener) {
        this.mealPlanList = mealPlanList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mealpaln_item, parent, false);
        return new MealPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealPlanViewHolder holder, int position) {
        MealPlan mealPlan = mealPlanList.get(position);

        // Bind data to UI elements
        holder.mealNameTextView.setText(mealPlan.getMealName());

        // Load the meal image using Glide
        Glide.with(context)
                .load(mealPlan.getMealImage())
                .placeholder(R.drawable.foods)
                .into(holder.mealImageView);

        // Handle delete click event
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(mealPlan));
    }

    @Override
    public int getItemCount() {
        return mealPlanList.size();
    }

    public void setMealPlans(List<MealPlan> mealPlans) {
        this.mealPlanList = mealPlans;
        notifyDataSetChanged();
    }

    // ViewHolder class to hold each card view
    public static class MealPlanViewHolder extends RecyclerView.ViewHolder {

        ImageView mealImageView;
        TextView mealNameTextView;
        ImageButton deleteButton;

        public MealPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImageView = itemView.findViewById(R.id.favrecipe_image);
            mealNameTextView = itemView.findViewById(R.id.favrecipe_name);
            deleteButton = itemView.findViewById(R.id.fav_recipe_favorite_button);
        }
    }
}
