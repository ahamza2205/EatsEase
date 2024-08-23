package com.example.eatsease.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "favorite_meals",primaryKeys = {"mealName","mealId"})
public class FavoriteMeal {


    @NonNull
    private String mealName;
    @NonNull
    private  String mealId;
    private String Thumbnail;
    private String userId;


    public FavoriteMeal() {
        this.mealName = mealName;
    }

    public FavoriteMeal(String mealName, String Thumbnail, String userId, String mealId) {
        this.mealName = mealName;
        this.Thumbnail = Thumbnail;
        this.userId = userId;
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

    public void setThumbnail(String thumbnail) {
        this.Thumbnail = thumbnail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId =mealId;
}
}