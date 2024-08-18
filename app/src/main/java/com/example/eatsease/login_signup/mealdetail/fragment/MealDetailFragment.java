package com.example.eatsease.login_signup.mealdetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.mealdetail.Ingredient;
import com.example.eatsease.login_signup.mealdetail.adapter.IngredientAdapter;

import java.util.ArrayList;
import java.util.List;

public class MealDetailFragment extends Fragment {

    private ImageView mealImage;
    private TextView mealTitle, instructions;
    private RecyclerView ingredientRecyclerView;
    private VideoView mealVideo;
    private Button addToCalendarBtn, addToFavoritesBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_detail, container, false);

        // Initialize views
        mealImage = view.findViewById(R.id.mealImage);
        mealTitle = view.findViewById(R.id.mealTitle);
        instructions = view.findViewById(R.id.instructions);
        ingredientRecyclerView = view.findViewById(R.id.ingredientRecyclerView);
        mealVideo = view.findViewById(R.id.mealVideo);
        addToCalendarBtn = view.findViewById(R.id.addToCalendarBtn);
        addToFavoritesBtn = view.findViewById(R.id.addToFavoritesBtn);

        // Setup RecyclerView
        setupIngredientRecyclerView();

        // Set data (replace with actual data fetching)
        mealTitle.setText("Salmon Eggs Benedict");
        instructions.setText("Instructions for making the meal...");
        mealImage.setImageResource(R.drawable.foods); // Placeholder image

        // Setup VideoView
      //  mealVideo.setVideoPath("path_to_video");  // Set the video path
       // mealVideo.start();

        return view;
    }

    private void setupIngredientRecyclerView() {
        // Set layout manager to horizontal
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Set adapter
        IngredientAdapter adapter = new IngredientAdapter(getIngredients());
        ingredientRecyclerView.setAdapter(adapter);
    }

    private List<Ingredient> getIngredients() {
        // Replace this with actual data fetching
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Eggs", R.drawable.foods));
        ingredients.add(new Ingredient("White Wine Vinegar", R.drawable.foods));
        ingredients.add(new Ingredient("English Muffins", R.drawable.foods));
        ingredients.add(new Ingredient("Butter", R.drawable.foods));
        ingredients.add(new Ingredient("Smoked Salmon", R.drawable.foods));
        return ingredients;
    }
}
