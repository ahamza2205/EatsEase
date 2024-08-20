package com.example.eatsease.home.ui.fragment;

import com.example.eatsease.home.model.response.CategoriesResponse;
import com.example.eatsease.home.model.response.MealsResponse;
public interface MealView {
    void onFetchDataSuccess(CategoriesResponse categoriesResponse);
    void onMealsSuccess(MealsResponse meals);
    void onFetchDataError(String errorMessage);
}
