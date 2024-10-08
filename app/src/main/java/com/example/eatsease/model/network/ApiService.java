package com.example.eatsease.model.network;

import com.example.eatsease.model.network.response.AreaResponse;
import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.CategoryResponse;
import com.example.eatsease.model.network.response.IngredienttsResponse;
import com.example.eatsease.model.network.response.RandomMealResponse;
import com.example.eatsease.model.network.response.MealsResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

 // 1 - get the list of categories
 @GET("categories.php")
 Observable<CategoriesResponse> getMealCategories();

 // 2 -  get seafoodoods
 @GET("filter.php")
 Single<MealsResponse> getMealsByCategory(@Query("c") String category);

 // 3 -  get a random meal
 @GET("random.php")
 Single<RandomMealResponse> getRandomMeal();

 // 4 - get a random meal
 @GET("lookup.php")
 Single<MealsResponse> getMealById(@Query("i") String mealId);

 // 5 -  get the list of categories
 @GET("list.php?c=list")
 Single<CategoryResponse> getMealCategoriesList();

 // 6 -  get the list of meal areas
 @GET("list.php?a=list")
 Single<AreaResponse> getMealAreasList();

 // 7 -  get the list of ingredients
 @GET("list.php?i=list")
 Single<IngredienttsResponse> getIngredientsList();

 // 8 -  get recipes by area
 @GET("filter.php")
 Single<MealsResponse> getMealsByArea(@Query("a") String area);

 // 9 -  get recipes by ingredient
 @GET("filter.php")
 Single<MealsResponse> getMealsByIngredient(@Query("i") String ingredient);
}
