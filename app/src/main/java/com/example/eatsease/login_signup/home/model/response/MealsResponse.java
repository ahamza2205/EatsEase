package com.example.eatsease.login_signup.home.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Meals Response Model
public class MealsResponse {
    @SerializedName("meals")
    private List<Meal> meals;
    public List<Meal> getMeals() {
        return meals;
    }
}
