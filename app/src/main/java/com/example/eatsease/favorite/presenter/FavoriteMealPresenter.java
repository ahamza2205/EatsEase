package com.example.eatsease.favorite.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.favorite.view.IFavMealView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealPresenter {
    private Respiratory repository;
    private IFavMealView view;
    private FavoriteMeal favoriteMeal;
    public FavoriteMealPresenter(Respiratory repository, IFavMealView view) {
        this.repository = repository;
        this.view = view;
    }

    public void fetchFavoriteMeals() {
        repository.getFavoriteMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favoriteMeals -> {
                            Log.d("FavoriteFragment", "Fetched " + favoriteMeals.size() + " meals for user: " );
                            view.onFetchDataSuccess(favoriteMeals);
                        },
                        throwable -> {
                            Log.e("FavoriteFragment", "Error fetching meals: " + throwable.getMessage());
                            view.onFetchDataError(throwable.getMessage());
                        }
                );
    }

    public void deleteFavoriteMeal(FavoriteMeal favoriteMeal) {
        repository.removeFavoriteMeal(favoriteMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Log.d("FavoriteFragment", "Meal deleted successfully"),
                        throwable -> Log.e("FavoriteFragment", "Error deleting meal: " + throwable.getMessage())
                );
    }

}
