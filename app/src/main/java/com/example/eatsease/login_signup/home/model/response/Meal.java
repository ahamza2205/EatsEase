package com.example.eatsease.login_signup.home.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Meal Data Model
public class Meal {
    @SerializedName("strMeal")
    private String mealName;

    @SerializedName("strMealThumb")
    private String mealThumbnail;

    @SerializedName("idMeal")
    private String mealId;

    // Getters
    public String getMealName() {
        return mealName;
    }

    public String getMealThumbnail() {
        return mealThumbnail;
    }

    public String getMealId() {
        return mealId;
    }
}

