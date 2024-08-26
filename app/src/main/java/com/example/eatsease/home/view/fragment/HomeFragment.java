package com.example.eatsease.home.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsease.R;
import com.example.eatsease.home.view.fragment.adapter.categories.CategoryAdapter;
import com.example.eatsease.home.view.fragment.adapter.Recipe.RecipeAdapter;
import com.example.eatsease.home.view.fragment.adapter.random.RandomAdapter;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.CategoryResponse;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.model.network.response.MealsResponse;
import com.example.eatsease.home.presenter.HomePresenter;
import com.example.eatsease.model.respiratory.Respiratory;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements MealView, RecipeAdapter.OnRecipeClickListener {

    private HomePresenter presenter;
    private RecyclerView categoryRecyclerView, recipeRecyclerView, randomRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecipeAdapter recipeAdapter;
    private RandomAdapter randomAdapter;
    private List<Meal> recipeList;
    private TextView recipesTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this, Respiratory.getInstance(this.getContext()), RetrofitClient.getInstance(), getContext());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Initialize Title TextView
        recipesTitle = view.findViewById(R.id.recipesTitle);

        // Initialize Category RecyclerView
        categoryRecyclerView = view.findViewById(R.id.recycler_categories);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Initialize Recipe RecyclerView
        recipeRecyclerView = view.findViewById(R.id.recycler_recipes);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recipeAdapter = new RecipeAdapter(recipeList, getContext(), this);
        recipeRecyclerView.setAdapter(recipeAdapter);

        // Initialize Random RecyclerView
        randomRecyclerView = view.findViewById(R.id.recycler_meals);
        randomRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        randomAdapter = new RandomAdapter(getContext());
        randomRecyclerView.setAdapter(randomAdapter);

        presenter.fetchMealCategories();

        return view;
    }

    // Handle category selection
    private void onCategorySelected(CategoryResponse category) {
        // Update the title based on selected category
        if (recipesTitle != null) {
            recipesTitle.setText(category.strCategory);
        }

        // Update recipe list based on the selected category
        getRecipesForCategory(category.strCategory);
    }

    private void getRecipesForCategory(String categoryName) {
        presenter.fetchSeafoodMeals(categoryName);
    }

    @Override
    public void onFetchDataSuccess(CategoriesResponse categoriesResponse) {
        categoryAdapter = new CategoryAdapter(categoriesResponse, getContext(), this::onCategorySelected);
        categoryRecyclerView.setAdapter(categoryAdapter);

        // Automatically select the first category
        if (categoriesResponse.categories != null && !categoriesResponse.categories.isEmpty()) {
            onCategorySelected(categoriesResponse.categories.get(0));
        }
    }

    @Override
    public void onMealsSuccess(MealsResponse meals) {
        recipeList = meals.getMeals();
        recipeAdapter.updateRecipeList(recipeList);
    }

    @Override
    public void onFetchDataError(String errorMessage) {
showNoInternetDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clear();
        randomAdapter.clear();  // Clear the disposables in the RandomAdapter
    }

    @Override
    public void onRecipeClick(Meal meal) {
        HomeFragmentDirections.ActionHomeFragmentToMealDetailFragment action =
                HomeFragmentDirections.actionHomeFragmentToMealDetailFragment(meal.getMealId());
        Navigation.findNavController(requireView()).navigate(action);
    }

    @Override
    public void onFavoriteClick(FavoriteMeal meal) {
        Log.d("updateMeal", "onFavoriteClick:"+meal.getMealId());
        presenter.insert(meal);
    }

    public void showNoInternetDialog() {
        // Inflate the custom dialog layout
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View dialogView = inflater.inflate(R.layout.dialog_custom, null);

        // Build the AlertDialog with the custom style
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext(), R.style.CustomAlertDialog);
        builder.setView(dialogView);

        // Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        // Set up the dialog views (similar to the previous example)
        ImageView dialogImage = dialogView.findViewById(R.id.dialogImage);
        TextView dialogTitle = dialogView.findViewById(R.id.dialogTitle);
        TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
        Button dialogButton = dialogView.findViewById(R.id.dialogButton);

        // Customize dialog content
        dialogImage.setImageResource(R.drawable.wifi);
        dialogTitle.setText("No Internet Connection");
        dialogMessage.setText("Please check your connection and try again.");

        // Set button click listener
        dialogButton.setOnClickListener(v -> alertDialog.dismiss());

        // Show the dialog
        alertDialog.show();
    }

}
