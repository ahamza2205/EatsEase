package com.example.eatsease.login_signup.home.ui.fragment;

import com.example.eatsease.login_signup.home.model.response.CategoriesResponse;
import com.example.eatsease.login_signup.home.model.response.MealsResponse;


public interface MealView {
    void onFetchDataSuccess(CategoriesResponse categoriesResponse);
    void onMealsSuccess(MealsResponse meals);
    void onFetchDataError(String errorMessage);
}
