package com.example.eatsease.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import com.example.eatsease.R;
import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.network.response.AreaResponse;
import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.CategoryResponse;
import com.example.eatsease.model.network.response.Ingredientt;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.search.view.adapter.AreaSearchItemsAdapter;
import com.example.eatsease.search.view.adapter.CategorySearchItemsAdapter;
import com.example.eatsease.search.presenter.SearchPresenter;
import com.example.eatsease.search.view.adapter.IngredientSearchItemsAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements ISearchView {

    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private CategorySearchItemsAdapter categoryAdapter;
    private AreaSearchItemsAdapter areaAdapter;
    private IngredientSearchItemsAdapter ingredientAdapter;
    private CategoriesResponse categoriesResponse;
    private List<AreaResponse.Area> areaList = new ArrayList<>();
    private List<Ingredientt> ingredientsList = new ArrayList<>();
    private SearchPresenter presenter;

    //chips
    Chip area,ingredients, categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search2, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chipGroup = view.findViewById(R.id.chipGroup);
        recyclerView = view.findViewById(R.id.recyclerView);

        area = view.findViewById(R.id.area);
        ingredients = view.findViewById(R.id.ingredient);
        categories = view.findViewById(R.id.categories);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter = new CategorySearchItemsAdapter(new CategoriesResponse(), getContext());
        areaAdapter = new AreaSearchItemsAdapter(new ArrayList<>(), getContext());
        ingredientAdapter = new IngredientSearchItemsAdapter(new ArrayList<>(), getContext()); // Initialize the ingredientAdapter

        recyclerView.setAdapter(categoryAdapter);  // Initially set to categoryAdapter

        presenter = new SearchPresenter(this, new Respiratory(getContext()), new RetrofitClient());
        presenter.fetchMealCategories();
        presenter.fetchMealAreas();
        presenter.fetchIngredientsList();

        updateChipsItems();
    }



    private void updateChipsItems() {
         {
            for (int i = 0; i < chipGroup.getChildCount(); i++) {
                Chip chip = (Chip) chipGroup.getChildAt(i);
                chip.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                    if (isChecked) {
                        String filterBy = chip.getText().toString();
                        switch (filterBy) {
                            case "Category":
                                recyclerView.setAdapter(categoryAdapter);
                                updateRecyclerViewWithCategories(categoriesResponse.getCategories());  // Ensure the correct getter is used
                                break;
                            case "Area":
                                recyclerView.setAdapter(areaAdapter);
                                updateRecyclerViewWithAreas(areaList);
                                break;
                            case "Ingredients":
                                recyclerView.setAdapter(ingredientAdapter);
                                updateRecyclerViewWithIngredients(ingredientsList);
                                break;
                        }
                    }
                });
            }
        }
    }

    private void updateRecyclerViewWithCategories(List<CategoryResponse> filteredList) {
        CategoriesResponse filteredCategoriesResponse = new CategoriesResponse();
        //filteredCategoriesResponse.setCategories(filteredList);  // Ensure correct setter is used
        categoryAdapter.setCategoryList(filteredCategoriesResponse);
        categoryAdapter.notifyDataSetChanged();  // Notify the adapter of data changes
    }
    private void updateRecyclerViewWithAreas(List<AreaResponse.Area> areas) {
        areaAdapter.setAreaList(areas);
        areaAdapter.notifyDataSetChanged();  // Notify the adapter of data changes
    }

    private void updateRecyclerViewWithIngredients(List<Ingredientt> ingredients) {
        ingredientAdapter.setIngredientt(ingredients);
        ingredientAdapter.notifyDataSetChanged();  // Notify the adapter of data changes
    }

    @Override
    public void onFetchCategoriesSuccess(CategoriesResponse categoriesResponse) {
        this.categoriesResponse = categoriesResponse;
        categoryAdapter.setCategoryList(categoriesResponse);
        //updateChipsItems();
    }

    @Override
    public void onFetchAreasSuccess(List<AreaResponse.Area> areas) {
        this.areaList = areas;
        // Ensure chips are updated with listeners
    }
    @Override
    public void onFetchCategoriesError(Throwable throwable) {
        Log.e("SearchFragment", "Error fetching categories", throwable);
    }
    @Override
    public void onFetchAreasError(Throwable throwable) {
        Log.e("SearchFragment", "Error fetching areas", throwable);
    }

    public void onIngredientsFetched(List<Ingredientt> ingredients) {
        this.ingredientsList = ingredients;  // Update the list in the fragment
        ingredientAdapter.setIngredientt(ingredients);  // Set the data in the adapter
    }

    @Override
    public void onFetchIngredientsError(String message) {

    }
}
