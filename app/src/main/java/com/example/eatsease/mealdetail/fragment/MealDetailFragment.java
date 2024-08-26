package com.example.eatsease.mealdetail.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eatsease.R;
import com.example.eatsease.login_signup.authentication.activity.login.LogIn;
import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPerferencesImp;
import com.example.eatsease.mealdetail.presenter.MealDetailPresenter;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.mealdetail.adapter.IngredientAdapter;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.plan.model.MealPlanRepository;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MealDetailFragment extends Fragment implements IMealDetailView {

    private ImageView mealImage;
    private TextView mealTitle, instructions;
    private RecyclerView ingredientRecyclerView;
    private IngredientAdapter ingredientAdapter;
    private YouTubePlayerView mealVideo;
    private Button addToCalendarBtn , addToFavoritesBtn;

    MealDetailPresenter presenter;
    private final CompositeDisposable disposables = new CompositeDisposable(); // Manage RxJava disposables

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_detail, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        presenter = new MealDetailPresenter(this,
                Respiratory.getInstance(getContext()),
                RetrofitClient.getInstance(),
                MealPlanRepository.getInstance(getContext()),
                getContext(),
                this);

        // Retrieve mealId from arguments
        if (getArguments() != null) {
            MealDetailFragmentArgs args = MealDetailFragmentArgs.fromBundle(getArguments());
            String mealId = args.getMealId(); // Correct getter method: getMealId()
            presenter.fetchDetailsmeal(mealId); // Ensure fetchDetailsMeal() accepts mealId
        }
    }

    public  String extractVideoId(String url) {
        // Split the URL on '?' to get the query part
        String[] parts = url.split("\\?");
        if (parts.length > 1) {
            // Split the query part on '&' to get individual parameters
            String query = parts[1];
            String[] queryParams = query.split("&");
            for (String param : queryParams) {
                // Split each parameter on '=' to separate key and value
                String[] keyValue = param.split("=");
                if (keyValue.length == 2 && "v".equals(keyValue[0])) {
                    return keyValue[1]; // Return the value of the 'v' parameter
                }
            }
        }
        return null; // Return null if the video ID is not found
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
            getLifecycle().addObserver(mealVideo);
            mealVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    //if your url is something like this -> https://www.youtube.com/watch?v=EzyXVfyx7CU
                    Log.d("MealVideo", "updateMeal:"+extractVideoId(meal.getYoutubeUrl()));
                    Log.d("MealVideo", "updateMeal:"+meal.getYoutubeUrl());

                    youTubePlayer.loadVideo(extractVideoId(meal.getYoutubeUrl()), 0);
                }
            });
        }

        // Handle addToFavoritesBtn
          addToFavoritesBtn.setOnClickListener(v -> {
              FirebaseUser currentUser = FirebaseAuthManager.getInstance().getCurrentUser();
              if (currentUser == null || currentUser.isAnonymous()) {
                  // User is not logged in or is using anonymous authentication, show the dialog
                  showCustomDialog();
              } else {
                  // Create a FavoriteMeal object based on the current Meal
                  FavoriteMeal favoriteMeal = new FavoriteMeal();
                  favoriteMeal.setMealId(meal.getMealId());
                  favoriteMeal.setMealName(meal.getMealName());
                  favoriteMeal.setThumbnail(meal.getMealThumbnail());

                  // Insert the favorite meal using HomePresenter
                  presenter.insert(favoriteMeal);
                  Toast.makeText(getContext(), "Meal Added to favorites", Toast.LENGTH_SHORT).show();
              }
          });


        addToCalendarBtn.setOnClickListener(v -> {
            FirebaseUser currentUser = FirebaseAuthManager.getInstance().getCurrentUser();
            if (currentUser == null || currentUser.isAnonymous()) {
                // User is not logged in or is using anonymous authentication, show the dialog
                showCustomDialog();
            } else {

                // Retrieve the meal ID and current date (you can customize the date handling)
                Log.d("MealDetailFragment", "clicked");
                String mealId = meal.getMealId();
                MaterialDatePicker<Long> m = MaterialDatePicker
                        .Builder
                        .datePicker()
                        .build();
                m.show(getParentFragmentManager(), "MaterialDatePicker");
                m.addOnPositiveButtonClickListener(selection -> {
                    presenter.addMealToCalendar(mealId, convertLongToDate(selection));
                });
            }
            });
    }

    private String convertLongToDate(long timestamp) {

                // Create a Date object from the timestamp
        Date date = new Date(timestamp);

        // Create a SimpleDateFormat object with the desired date pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Format the Date object to the desired string format
        return dateFormat.format(date);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear(); // Clear RxJava disposables when view is destroyed
    }


    @Override
    public void showMealPlanAdded() {
        if (isAdded() && getContext() != null) {
            Toast.makeText(getContext(), "Meal plan added", Toast.LENGTH_SHORT).show();
        } else {
            // Handle the case when Fragment is not attached or Context is null
            Log.e("MealDetailFragment", "Fragment is not attached or Context is null");
        }
    }


    @Override
    public void showMealPlanError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }



    private void showCustomDialog() {
        // Inflate the custom dialog layout
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View dialogView2 = inflater.inflate(R.layout.dialog_login, null);

        // Build the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext(), R.style.CustomAlertDialog);
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
            SharedPerferencesImp sharedPrefRespiratory = SharedPerferencesImp.getInstance(this.getContext());
            sharedPrefRespiratory.removePreferences();

            // Navigate to the login activity
            Intent intent = new Intent(this.getContext(), LogIn.class);
            this.startActivity(intent);
        });

        // Show the dialog
        alertDialog.show();
    }
}
