package com.example.eatsease.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "favorite_meals", primaryKeys = {"mealName", "mealId"})
public class FavoriteMeal {

    @NonNull
    private String mealName;
    @NonNull
    private String mealId;
    private String Thumbnail;
    private String userEmail;  // Renamed from userId to userEmail

    public FavoriteMeal() {
        this.mealName = mealName;
    }

    public FavoriteMeal(String mealName, String Thumbnail, String userEmail, String mealId) {
        this.mealName = mealName;
        this.Thumbnail = Thumbnail;
        this.userEmail = userEmail;  // Updated to userEmail
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String Thumbnail) {
        this.Thumbnail = Thumbnail;
    }

    public String getUserEmail() {
        return userEmail;  // Updated to userEmail
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;  // Updated to userEmail
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
