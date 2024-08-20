package com.example.eatsease.login_signup.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eatsease.R;
import com.example.eatsease.login_signup.home.adapter.Recipe.RecipeAdapter;
import com.example.eatsease.login_signup.home.adapter.categories.CategoryAdapter;
import com.example.eatsease.login_signup.home.adapter.random.RandomAdapter;
import com.example.eatsease.login_signup.home.model.response.CategoriesResponse;
import com.example.eatsease.login_signup.home.model.response.CategoryResponse;
import com.example.eatsease.login_signup.home.model.response.Meal;
import com.example.eatsease.login_signup.home.model.response.MealsResponse;
import com.example.eatsease.login_signup.home.presenter.HomePresenter;

import java.util.List;

public class HomeFragment extends Fragment implements MealView {

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
        presenter = new HomePresenter(this);
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
        recipeAdapter = new RecipeAdapter(recipeList, getContext());
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
        // Handle errors here (e.g., display a message to the user)
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clear();
        randomAdapter.clear();  // Clear the disposables in the RandomAdapter
    }
}
