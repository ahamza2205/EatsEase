package com.example.eatsease.home.view.fragment.adapter.random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.home.view.fragment.HomeFragmentDirections;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.model.network.response.RandomMealResponse;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.RandomViewHolder> {

    private Meal currentMeal;
    private Context context;
    private Respiratory respiratory;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RandomAdapter(Context context) {
        this.context = context;
        this.respiratory = new Respiratory(context); // Initialize the Respiratory
        fetchRandomMeal(); // Fetch the first meal when the adapter is created
    }

    @NonNull
    @Override
    public RandomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rondom, parent, false);
        return new RandomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomViewHolder holder, int position) {
        if (currentMeal != null) {
            holder.mealName.setText(currentMeal.getMealName());
            Glide.with(context)
                    .load(currentMeal.getMealThumbnail())
                    .into(holder.mealImage);
        }

        // Set click listener for each recipe item
        holder.itemView.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToMealDetailFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealDetailFragment(currentMeal.getMealId());
            Navigation.findNavController(holder.itemView).navigate(action);
        });
    }



    @Override
    public int getItemCount() {
        return currentMeal != null ? 1 : 0;
    }

    private void fetchRandomMeal() {
        compositeDisposable.add(respiratory.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RandomMealResponse>() {
                    @Override
                    public void accept(RandomMealResponse randomMealResponse) throws Throwable {
                        if (randomMealResponse.getMeals() != null && !randomMealResponse.getMeals().isEmpty()) {
                            currentMeal = randomMealResponse.getMeals().get(0);
                            notifyDataSetChanged();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Toast.makeText(context, "Failed to fetch meal", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    public void clear() {
        compositeDisposable.clear();
    }

    public static class RandomViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        ImageView mealImage;

        public RandomViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.meal_name);
            mealImage = itemView.findViewById(R.id.meal_image);
        }
    }
}
