package com.example.eatsease.login_signup.home.model.repo;

import com.example.eatsease.login_signup.home.model.response.CategoriesResponse;
import com.example.eatsease.login_signup.home.model.network.HomeApi;
import com.example.eatsease.login_signup.home.model.response.MealsResponse;
import com.example.eatsease.login_signup.home.model.response.RandomMealResponse;
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
}
