package com.example.eatsease.search.presenter;

import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.search.view.ISearchView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    private  ISearchView view;
    private  Respiratory respiratory;
    private  RetrofitClient retrofitClient ;
    private CompositeDisposable disposable;

    public SearchPresenter(ISearchView view , Respiratory respiratory , RetrofitClient retrofitClient) {
        this.respiratory = respiratory;
        this.view = view;
        this.retrofitClient = retrofitClient;
        this.disposable =  new CompositeDisposable();
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
