package com.example.eatsease.mealdetail.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.RetrofitClient;
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
    private RetrofitClient retrofitClient ;
    private CompositeDisposable disposable;
    private Context context;

    public MealDetailPresenter(MealDetailFragment mealDetialFragment, Respiratory repo, RetrofitClient retrofitClient, Context context) {
        this.mealDetialFragment = mealDetialFragment;
        this.repo = repo;
        this.retrofitClient = retrofitClient;
        this.disposable = new CompositeDisposable();
        this.context = context; // Initialize context
    }
    public void fetchDetailsmeal(String mealId) {
        disposable.add(
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

    public void insert(FavoriteMeal favoriteMeal) {
        repo.addFavoriteMeal(favoriteMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Toast.makeText(context, "Product Added To Database", Toast.LENGTH_SHORT).show();
                        },
                        throwable -> {
                            // Handle error
                            throwable.printStackTrace();
                        });
    }
}
