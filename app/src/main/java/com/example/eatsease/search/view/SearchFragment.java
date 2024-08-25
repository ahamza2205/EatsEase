package com.example.eatsease.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.example.eatsease.R;
import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.network.response.AreaResponse;
import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.CategoryResponse;
import com.example.eatsease.model.network.response.Ingredientt;
import com.example.eatsease.model.network.response.MealsResponse;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.search.view.adapter.AreaSearchItemsAdapter;
import com.example.eatsease.search.view.adapter.CategorySearchItemsAdapter;
import com.example.eatsease.search.presenter.SearchPresenter;
import com.example.eatsease.search.view.adapter.IngredientSearchItemsAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

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
    private AutoCompleteTextView searchAutoCompleteTextView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PublishSubject<String> searchSubject = PublishSubject.create();
    private Chip area, ingredients, categories;

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
        ingredientAdapter = new IngredientSearchItemsAdapter(new ArrayList<>(), getContext());

        recyclerView.setAdapter(categoryAdapter);  // Initially set to categoryAdapter

        presenter = new SearchPresenter(this, new Respiratory(getContext()), new RetrofitClient());
        presenter.fetchMealCategories();
        presenter.fetchMealAreas();
        presenter.fetchIngredientsList();

        updateChipsItems();

        // Initialize AutoCompleteTextView
        searchAutoCompleteTextView = view.findViewById(R.id.searchAutoCompleteTextView);

        // Observe text changes using RxJava
        observeSearch();
    }

    private void updateChipsItems() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (isChecked) {
                    String filterBy = chip.getText().toString();
                    switch (filterBy) {
                        case "Category":
                            recyclerView.setAdapter(categoryAdapter);
                            updateRecyclerViewWithCategories(categoriesResponse.getCategories());
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

    private void updateRecyclerViewWithCategories(List<CategoryResponse> categoryResponses) {
        categoryAdapter.setCategoryList(new CategoriesResponse((ArrayList<CategoryResponse>) categoryResponses));
        categoryAdapter.notifyDataSetChanged();
    }

    private void updateRecyclerViewWithAreas(List<AreaResponse.Area> areas) {
        areaAdapter.setAreaList(areas);
        areaAdapter.notifyDataSetChanged();
    }

    private void updateRecyclerViewWithIngredients(List<Ingredientt> ingredients) {
        ingredientAdapter.setIngredientt(ingredients);
        ingredientAdapter.notifyDataSetChanged();
    }

    private void observeSearch() {
        searchAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchSubject.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        compositeDisposable.add(
                searchSubject
                        .distinctUntilChanged()  // No debounce
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(query -> searchItems(query), throwable -> {
                            Log.e("SearchFragment", "Search error", throwable);
                        })
        );
    }

    private void searchItems(String query) {
        if (recyclerView.getAdapter() == categoryAdapter) {
            List<CategoryResponse> filteredCategories = new ArrayList<>();
            for (CategoryResponse category : categoriesResponse.getCategories()) {
                if (category.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredCategories.add(category);
                }
            }
            updateRecyclerViewWithCategories(filteredCategories);
        } else if (recyclerView.getAdapter() == areaAdapter) {
            List<AreaResponse.Area> filteredAreas = new ArrayList<>();
            for (AreaResponse.Area area : areaList) {
                if (area.getAreaName().toLowerCase().contains(query.toLowerCase())) {
                    filteredAreas.add(area);
                }
            }
            updateRecyclerViewWithAreas(filteredAreas);
        } else if (recyclerView.getAdapter() == ingredientAdapter) {
            List<Ingredientt> filteredIngredients = new ArrayList<>();
            for (Ingredientt ingredient : ingredientsList) {
                if (ingredient.getStrIngredient().toLowerCase().contains(query.toLowerCase())) {
                    filteredIngredients.add(ingredient);
                }
            }
            updateRecyclerViewWithIngredients(filteredIngredients);
        }
    }

    @Override
    public void onFetchCategoriesSuccess(CategoriesResponse categoriesResponse) {
        this.categoriesResponse = categoriesResponse;
        categoryAdapter.setCategoryList(categoriesResponse);
    }

    @Override
    public void onFetchAreasSuccess(List<AreaResponse.Area> areas) {
        this.areaList = areas;
    }

    @Override
    public void onFetchCategoriesError(Throwable throwable) {
        Log.e("SearchFragment", "Error fetching categories", throwable);
    }

    @Override
    public void onFetchAreasError(Throwable throwable) {
        Log.e("SearchFragment", "Error fetching areas", throwable);
    }

    @Override
    public void onIngredientsFetched(List<Ingredientt> ingredients) {
        this.ingredientsList = ingredients;
        ingredientAdapter.setIngredientt(ingredients);
    }

    @Override
    public void onFetchIngredientsError(String message) {
        Log.e("SearchFragment", "Error fetching ingredients: " + message);
    }

    @Override
    public void onMealsSuccess(MealsResponse mealsResponse) {}

    @Override
    public void onFetchMealsError(Throwable throwable) {}

    @Override
    public void onAreaMealsSuccess(MealsResponse mealsResponse) {
        Log.d("hamza", "onAreaMealsSuccess: ");
    }

    @Override
    public void onFetchAreaMealsError(Throwable throwable) {
        Log.d("h", "on bs: ");
    }

    @Override
    public void onIngredientsMealsFetched(MealsResponse mealsResponse) {}

    @Override
    public void onFetchIngredientsMealsError(Throwable throwable) {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }
}