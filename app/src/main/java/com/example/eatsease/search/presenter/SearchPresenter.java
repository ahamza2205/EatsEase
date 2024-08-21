package com.example.eatsease.search.presenter;

import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.search.view.ISearchView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    private final ISearchView view;
    private final Respiratory respiratory;

    public SearchPresenter(ISearchView view) {
        this.view = view;
        this.respiratory = new Respiratory();
    }

    // Fetch meal categories
    public void fetchMealCategories() {
        respiratory.getMealCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categoriesResponse -> view.onFetchCategoriesSuccess(categoriesResponse),
                        throwable -> view.onFetchCategoriesError(throwable)
                );
    }

    // Fetch meal areas
    public void fetchMealAreas() {
        respiratory.getMealAreasList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        areaResponse -> view.onFetchAreasSuccess(areaResponse.getMeals()),  // Pass the list of areas
                        throwable -> view.onFetchAreasError(throwable)
                );
    }
}
