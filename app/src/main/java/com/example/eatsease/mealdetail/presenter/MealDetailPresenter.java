package com.example.eatsease.mealdetail.presenter;

import android.util.Log;

import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.mealdetail.fragment.MealDetailFragment;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailPresenter {
    private MealDetailFragment mealDetialFragment;
    private Respiratory repo;
    private final CompositeDisposable disposables = new CompositeDisposable();


    public MealDetailPresenter(MealDetailFragment mealDetialFragment) {
        this.mealDetialFragment = mealDetialFragment;
        this.repo = new Respiratory();
    }
    public void fetchDetailsmeal(String mealId) {
        disposables.add(
                repo.getMealById(mealId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                mealsResponse -> {
                                    List<Meal> meals = mealsResponse.getMeals();
                                    if (meals != null && !meals.isEmpty()) {
                                        Meal meal = meals.get(0);
                                        mealDetialFragment.updateMeal(meal);
                                    } else {
                                        // Handle empty list case
                                        Log.i("MealDetail", "No meal details found");
                                    }
                                },
                                throwable -> {
                                    Log.e("MealDetail", "Error fetching meal details: " + throwable.getMessage());
                                }
                        )
        );
    }
}
