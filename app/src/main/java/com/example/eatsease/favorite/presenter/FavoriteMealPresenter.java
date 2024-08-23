package com.example.eatsease.favorite.presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.eatsease.R;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.respiratory.Respiratory;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealPresenter {
    private Respiratory repository;
    private Context context;

    public FavoriteMealPresenter(Respiratory repository, Context context) {
        this.context = context;
        this.repository = new Repository(context);
    }

    public void delete(FavoriteMeal favoriteMeal) {
        repository.removeFavoriteMeal(favoriteMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show();
                        },
                        throwable -> {
                            // Handle error
                            throwable.printStackTrace();
                        });
    }
}
