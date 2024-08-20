package com.example.eatsease.home.model.network;

import com.example.eatsease.home.model.response.AreaResponse;
import com.example.eatsease.home.model.response.CategoriesResponse;
import com.example.eatsease.home.model.response.CategoryResponse;
import com.example.eatsease.home.model.response.MealsResponse;
import com.example.eatsease.home.model.response.RandomMealResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeApi {

   // 1 -  Existing endpoint to get the list of categories
    @GET("categories.php")
    Observable<CategoriesResponse> getMealCategories();

    // 2 -  New endpoint to get seafoodoods
    @GET("filter.php")
    Single<MealsResponse> getMealsByCategory(@Query("c") String category);

    // 3 -  New endpoint to get a random meal
    @GET("random.php")
    Single<RandomMealResponse> getRandomMeal();

    // 4 -  New endpoint to get a random meal
    @GET("lookup.php")
    Single<MealsResponse> getMealById(@Query("i") String mealId);

    // 5 -  Existing endpoint to get the list of categories
    @GET("list.php?c=list")
    Single<CategoryResponse> getMealCategoriesList();

    // 6 -  New endpoint to get the list of meal areas
    @GET("list.php?a=list")
    Single<AreaResponse> getMealAreasList();

    // 7 -  New endpoint to get the list of ingredients
    @GET("list.php?i=list")
    Single<IngredientListResponse> getIngredients();
}
