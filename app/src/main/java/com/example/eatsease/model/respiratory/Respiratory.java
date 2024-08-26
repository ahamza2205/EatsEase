package com.example.eatsease.model.respiratory;

import android.content.Context;


import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.database.MealsDataBase;
import com.example.eatsease.model.network.ApiService;
import com.example.eatsease.model.network.response.AreaResponse;
import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.CategoryResponse;
import com.example.eatsease.model.network.response.IngredienttsResponse;
import com.example.eatsease.model.network.response.RandomMealResponse;
import com.example.eatsease.model.network.response.MealsResponse;
import com.example.eatsease.model.network.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Respiratory {
    private ApiService api;
   private final MealsDataBase mealsDataBase ;
    private Context context;
    private  static Respiratory repo = null;

    public static Respiratory getInstance(Context context)
    {
        if (repo==null)
        {
            repo=new Respiratory(context);
        }
        return repo;
    }

    public Respiratory(Context context) {
        this.context = context;
        this.api = api;
        RetrofitClient retrofit = RetrofitClient.getInstance();
        api = retrofit.api;
        mealsDataBase = MealsDataBase.getInstance(context);
    }

    public Observable<CategoriesResponse> getMealCategories() {
        return api.getMealCategories();
    }

    public Single<MealsResponse> getSeafoodMeals(String categoryName) {
        return api.getMealsByCategory(categoryName);
    }

    public Single<RandomMealResponse> getRandomMeal() {
        return api.getRandomMeal();
    }
    public Single<MealsResponse>  getMealById(String id) {
        return api.getMealById(id);
     }

    // New method for fetching categories list
//    public Single<CategoryResponse> getMealCategoriesList() {
//        return api.getMealCategoriesList();
//    }

    // New method for fetching meal areas list
    public Single<AreaResponse> getMealAreasList() {
        return api.getMealAreasList();
    }
    // New method for fetching ingredients
    public Single<IngredienttsResponse> getIngredientsList() {
        return api.getIngredientsList();
    }
    // New method for fetching recipes by area
    public Single<MealsResponse> getMealsByArea(String area) {
        return api.getMealsByArea(area);
    }
   // New method for fetching recipes by ingredient
    public Single<MealsResponse> getMealsByIngredient(String ingredient) {
        return api.getMealsByIngredient(ingredient);
    }



    // Database methods for favorites
    public Completable addFavoriteMeal(FavoriteMeal favoriteMeal) {
        return mealsDataBase.favoriteMealDao().insert(favoriteMeal);
    }
    // Check if meal is favorite
//    public Single<Boolean> isFavoriteMeal(String mealId) {
//        return Single.fromCallable(() -> mealsDataBase.favoriteMealDao().getMealById(mealId) != null)
//                .subscribeOn(Schedulers.io());
//    }
   // Get all favorite meals
    public Flowable<List<FavoriteMeal>> getFavoriteMeals() {
        return mealsDataBase.favoriteMealDao().getAllFavoriteMeals()
                .subscribeOn(Schedulers.io());
    }
    // Remove favorite
    public Completable removeFavoriteMeal(FavoriteMeal favoriteMeal) {
        return mealsDataBase.favoriteMealDao().delete(favoriteMeal)
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<FavoriteMeal>> getFavoriteMealsByUserEmail(String userEmail) {
        return mealsDataBase.favoriteMealDao().getAllFavoriteMealsByUserEmail(userEmail)
                .subscribeOn(Schedulers.io());
    }
}
