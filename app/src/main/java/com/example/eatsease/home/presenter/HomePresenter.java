package com.example.eatsease.home.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.RetrofitClient;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.home.view.fragment.MealView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter {
    private final Respiratory repository;
    private final MealView view;
    private CompositeDisposable disposable;
    private RetrofitClient retrofitClient;
    private Context context;
    private SharedPreRespiratory sharedPreRespiratory;
    // private FavoriteMeal favoriteMeal ;

    public HomePresenter(MealView view, Respiratory repository, RetrofitClient retrofitClient, Context context ) {
        this.repository = repository;
        this.view = view;
        this.retrofitClient = retrofitClient;
        this.context = context; // Initialize context here
        this.disposable = new CompositeDisposable();
        this.sharedPreRespiratory = SharedPreRespiratory.getInstance(context);
    }

    public void fetchMealCategories() {

        if (retrofitClient == null) {
            return;
        }
        disposable.add(
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
        disposable.add(
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
        disposable.clear();
    }

    public void insert(FavoriteMeal favoriteMeal) {
        // Get the user's email from SharedPreferences
        String userEmail = sharedPreRespiratory.getUserEmail();

        // Set the user's email in the FavoriteMeal object
        favoriteMeal.setUserEmail(userEmail);

        // Insert the favorite meal into the database
        repository.addFavoriteMeal(favoriteMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            // Success handling (e.g., show a Toast)
                        },
                        throwable -> {
                            // Handle error
                            throwable.printStackTrace();
                        });
    }


    public void delete(FavoriteMeal favoriteMeal) {
        repository.removeFavoriteMeal(favoriteMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                           // Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show();
                        },
                        throwable -> {
                            // Handle error
                            throwable.printStackTrace();
                        });
    }
}
