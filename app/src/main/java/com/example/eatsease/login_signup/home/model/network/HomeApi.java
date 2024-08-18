package com.example.eatsease.login_signup.home.model.network;

import com.example.eatsease.login_signup.home.model.response.CategoriesResponse;
import com.example.eatsease.login_signup.home.model.response.MealsResponse;
import com.example.eatsease.login_signup.home.model.response.RandomMealResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeApi {

    @GET("categories.php")
    Observable<CategoriesResponse> getMealCategories();

    @GET("filter.php")
    Single<MealsResponse> getMealsByCategory(@Query("c") String category);

    @GET("random.php")
    Single<RandomMealResponse> getRandomMeal();
}
