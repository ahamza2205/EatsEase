package com.example.eatsease.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.eatsease.R;
import com.example.eatsease.home.view.fragment.adapter.Recipe.RecipeAdapter;
import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.network.response.AreaResponse;
import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.Ingredientt;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.model.network.response.MealsResponse;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.search.presenter.SearchPresenter;
import com.example.eatsease.search.view.ISearchView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchItemFragment extends Fragment implements ISearchView {

    private SearchPresenter presenter;
    private RecipeAdapter recipeAdapter , recipeAreaAdapter;
    private RecyclerView recyclerView;
     private List<Meal> newRecipes ;
     private String strCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize presenter here, passing the fragment as a view
      //  presenter = new SearchPresenter(this , Respiratory.getInstance(getContext()) , RetrofitClient.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize RecyclerView and Adapter
        recyclerView = view.findViewById(R.id.recycleSearch);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Initialize the adapter with an empty list
        recipeAdapter = new RecipeAdapter(new ArrayList<>(), getContext());
        recyclerView.setAdapter(recipeAdapter);


            presenter = new SearchPresenter(this , Respiratory.getInstance(this.getContext()) , RetrofitClient.getInstance());


            // Fetch data from arguments
        SearchItemFragmentArgs args = SearchItemFragmentArgs.fromBundle(getArguments());
        String categoryName = args.getStrCategory();
        String areaName = args.getStrArea();
        String ingredientName = args.getStrIngredient();
        Log.d("TEST_AREA", "Fetching seafood meals for category: " + categoryName);
        if (categoryName != null) {
            Log.d("SearchItemFragment", "Fetching seafood meals for category: " + categoryName);
            presenter.fetchSeafoodMeals(categoryName);
        }

        if (areaName != null) {
            Log.d("SearchItemFragment", "Fetching meals for area: " + areaName);
            presenter.getCanadianMeals(areaName);
        }
          if (ingredientName != null) {
              Log.d("SearchItemFragment", "Fetching recipes for ingredient: " + ingredientName);
              presenter.getMealsByIngredient(ingredientName);
          }
        }


    @Override
    public void onFetchCategoriesSuccess(CategoriesResponse categoriesResponse) {

    }

    @Override
    public void onFetchCategoriesError(Throwable throwable) {

    }

    @Override
    public void onFetchAreasSuccess(List<AreaResponse.Area> areas) {

    }

    @Override
    public void onFetchAreasError(Throwable throwable) {

    }

    @Override
    public void onIngredientsFetched(List<Ingredientt> ingredients) {

    }

    @Override
    public void onFetchIngredientsError(String message) {

    }

    @Override
    public void onMealsSuccess(MealsResponse mealsResponse) {
        newRecipes = mealsResponse.getMeals();
        Log.d("Zd", "onMealsSuccess: "+mealsResponse.getMeals());
        recipeAdapter.updateRecipeList(newRecipes);
    }

    @Override
    public void onFetchMealsError(Throwable throwable) {
        Log.e("SearchItemFragment", "Error fetching meals", throwable);
        Toast.makeText(getContext(), "Failed to load meals", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAreaMealsSuccess(MealsResponse mealsResponse) {
            Log.d("SearchItemFragment", "Received area meals: " + mealsResponse.getMeals());
            recipeAdapter.updateRecipeList(mealsResponse.getMeals());  // Use the correct adapter

    }

    @Override
    public void onFetchAreaMealsError(Throwable throwable) {
        Log.e("SearchItemFragment", "Error fetching meals", throwable);
        Toast.makeText(getContext(), "Failed to load meals", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIngredientsMealsFetched(MealsResponse mealsResponse) {
        Log.d("SearchItemFragment", "Received area meals: " + mealsResponse.getMeals());
        recipeAdapter.updateRecipeList(mealsResponse.getMeals());  // Use the correct adapter

    }

    @Override
    public void onFetchIngredientsMealsError(Throwable throwable) {
        Log.e("SearchItemFragment", "Error fetching meals", throwable);
        Toast.makeText(getContext(), "Failed to load meals", Toast.LENGTH_SHORT).show();
    }
}
