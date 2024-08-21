package com.example.eatsease.home.view.fragment;

import com.example.eatsease.model.network.response.CategoriesResponse;
import com.example.eatsease.model.network.response.MealsResponse;
public interface MealView {
    void onFetchDataSuccess(CategoriesResponse categoriesResponse);
    void onMealsSuccess(MealsResponse meals);
    void onFetchDataError(String errorMessage);
}
