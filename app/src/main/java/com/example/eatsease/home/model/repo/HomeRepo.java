package com.example.eatsease.home.model.repo;

import com.example.eatsease.home.model.response.AreaResponse;
import com.example.eatsease.home.model.response.CategoriesResponse;
import com.example.eatsease.home.model.network.HomeApi;
import com.example.eatsease.home.model.response.CategoryResponse;
import com.example.eatsease.home.model.response.MealsResponse;
import com.example.eatsease.home.model.response.RandomMealResponse;
import com.example.eatsease.login_signup.utils.RetrofitClient;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;

public class HomeRepo {
    private HomeApi api;

    public HomeRepo() {
        Retrofit retrofit = RetrofitClient.getInstance();
        api = retrofit.create(HomeApi.class);
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
    public Single<CategoryResponse> getMealCategoriesList() {
        return api.getMealCategoriesList();
    }

    // New method for fetching meal areas list
    public Single<AreaResponse> getMealAreasList() {
        return api.getMealAreasList();
    }
}
