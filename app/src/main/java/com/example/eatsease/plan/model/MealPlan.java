package com.example.eatsease.plan.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mealPlan")
public class MealPlan {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mealId;
    private String mealImage;
    private String mealName;
    private String date;
    private String userEmail;

    // Constructor, Getters, and Setters
    public MealPlan(String mealId, String mealImage, String mealName, String date) {
        this.mealId = mealId;
        this.mealImage = mealImage;
        this.mealName = mealName;
        this.date = date;
    }

    public MealPlan() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
