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

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.home.model.response.Meal;
import com.example.eatsease.login_signup.mealdetail.presenter.MealDetailPresenter;
import com.example.eatsease.login_signup.mealdetail.adapter.IngredientAdapter;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MealDetailFragment extends Fragment {

    private ImageView mealImage;
    private TextView mealTitle, instructions;
    private RecyclerView ingredientRecyclerView;
    private IngredientAdapter ingredientAdapter;
    private VideoView mealVideo;
    private Button addToCalendarBtn , addToFavoritesBtn;
    MealDetailPresenter presenter;
    private final CompositeDisposable disposables = new CompositeDisposable(); // Manage RxJava disposables

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

        // Setup RecyclerView for Ingredients
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ingredientAdapter = new IngredientAdapter(new ArrayList<>());
        ingredientRecyclerView.setAdapter(ingredientAdapter);

        presenter = new MealDetailPresenter(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve mealId from arguments
        if (getArguments() != null) {
            MealDetailFragmentArgs args = MealDetailFragmentArgs.fromBundle(getArguments());
            String mealId = args.getMealId(); // Correct getter method: getMealId()
            presenter.fetchDetailsmeal(mealId); // Ensure fetchDetailsMeal() accepts mealId
        }
    }

    public void updateMeal(Meal meal) {
        // Update meal details in the UI
        ingredientAdapter.updateIngredientDataList(meal.getIngredients());
        mealTitle.setText(meal.getMealName());
        instructions.setText(meal.getInstructions());
        Glide.with(getContext())
                .load(meal.getMealThumbnail())
                .into(mealImage);

        // Handle video if available
        if (meal.getYoutubeUrl() != null && !meal.getYoutubeUrl().isEmpty()) {
            mealVideo.setVideoPath(meal.getYoutubeUrl());
            mealVideo.start();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear(); // Clear RxJava disposables when view is destroyed
    }
}
