package com.example.eatsease.mealdetail.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.eatsease.mealdetail.fragment.IMealDetailView;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.mealdetail.fragment.MealDetailFragment;
import com.example.eatsease.plan.model.MealPlan;
import com.example.eatsease.plan.model.MealPlanRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailPresenter {
    private MealDetailFragment mealDetialFragment;
    private Respiratory repo;
    private RetrofitClient retrofitClient;
    private CompositeDisposable disposable;
    private Context context;
    private MealPlanRepository mealPlanRepository;
    private IMealDetailView view;

    public MealDetailPresenter(MealDetailFragment mealDetialFragment, Respiratory repo, RetrofitClient retrofitClient, MealPlanRepository mealPlanRepository, Context context, IMealDetailView view) {
        this.mealDetialFragment = mealDetialFragment;
        this.repo = repo;
        this.retrofitClient = retrofitClient;
        this.mealPlanRepository = mealPlanRepository; // Initialize the repository
        this.disposable = new CompositeDisposable();
        this.context = context;
        this.view = view;
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

    public void addMealToCalendar(String mealId, String date) {
        disposable.add(
                repo.getMealById(mealId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsResponse -> {
                            List<Meal> meals = mealsResponse.getMeals();
                            if (meals != null && !meals.isEmpty()) {
                                Meal meal = meals.get(0);

                                // Create a new MealPlan object
                                MealPlan mealPlan = new MealPlan();
                                mealPlan.setMealId(meal.getMealId());
                                mealPlan.setMealName(meal.getMealName());
                                mealPlan.setMealImage(meal.getMealThumbnail());
                                mealPlan.setDate(date); // Set the selected date

                                // Notify view of success
                                mealPlanRepository.saveMealPlan(mealPlan).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(() -> {
                                            // Notify view of success
                                            view.showMealPlanAdded();
                                        }, throwable -> {
                                            view.showMealPlanError("Failed to add meal to calendar.");
                                        });


                            } else {
                                view.showMealPlanError("Failed to fetch meal details.");
                            }
                        }, throwable -> {
                            view.showMealPlanError("Failed to fetch meal details.");
                        })
        );
    }


}
