package com.example.eatsease.home.presenter;

import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.home.view.fragment.MealView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private final Respiratory repository;
    private final MealView view;
    private final CompositeDisposable disposables = new CompositeDisposable(); // Manage RxJava disposables

    public HomePresenter(MealView view) {
        this.repository = new Respiratory();
        this.view = view;
    }
    public void fetchMealCategories() {
        disposables.add(
                repository.getMealCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::onFetchDataSuccess,
                                throwable -> {
                                    view.onFetchDataError(throwable.getMessage());
                                }
                        )
        );
    }
    public void fetchSeafoodMeals(String categoryName) {
        disposables.add(
                repository.getSeafoodMeals(categoryName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                view::onMealsSuccess,
                                throwable -> view.onFetchDataError(throwable.getMessage())
                        )
        );
    }


    public void clear() {
        disposables.clear();
    }
}
