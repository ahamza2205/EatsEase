package com.example.eatsease.login_signup.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.home.model.response.CategoriesResponse;
import com.example.eatsease.login_signup.home.model.response.CategoryResponse;
import com.example.eatsease.login_signup.home.model.response.Meal;
import com.example.eatsease.login_signup.home.model.response.MealsResponse;
import com.example.eatsease.login_signup.home.presenter.HomePresenter;
import com.example.eatsease.login_signup.home.ui.MealView;
import com.example.eatsease.login_signup.ui.fragment.Recipe.Recipe;
import com.example.eatsease.login_signup.ui.fragment.Recipe.RecipeAdapter;
import com.example.eatsease.login_signup.ui.fragment.categories.Category;
import com.example.eatsease.login_signup.ui.fragment.categories.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements MealView {

    private static final String TAG = "HOME_FRAGEMENT";
    private HomePresenter presenter;
    private RecyclerView categoryRecyclerView, recipeRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecipeAdapter recipeAdapter;
    private List<Meal> recipeList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this);
        presenter.fetchMealCategories();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Initialize Category RecyclerView
        categoryRecyclerView = view.findViewById(R.id.recycler_categories);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        // Initialize Recipe RecyclerView
        recipeRecyclerView = view.findViewById(R.id.recycler_recipes);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recipeAdapter = new RecipeAdapter(recipeList, getContext());
        recipeRecyclerView.setAdapter(recipeAdapter);

        return view;
    }

    // Handle category selection
    private void onCategorySelected(CategoryResponse category) {
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
        presenter.fetchSeafoodMeals("Beef");
    }

    @Override
    public void onMealsSuccess(MealsResponse meals) {
        recipeList = meals.getMeals();
        recipeAdapter.updateRecipeList(recipeList);
    }

    @Override
    public void onFetchDataError(String errorMessage) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.clear();
    }
}
