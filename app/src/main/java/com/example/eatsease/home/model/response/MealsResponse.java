package com.example.eatsease.home.model.response;

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
