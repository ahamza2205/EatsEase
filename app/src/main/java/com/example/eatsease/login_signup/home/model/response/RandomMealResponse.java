package com.example.eatsease.login_signup.home.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RandomMealResponse {
    @SerializedName("meals")
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
