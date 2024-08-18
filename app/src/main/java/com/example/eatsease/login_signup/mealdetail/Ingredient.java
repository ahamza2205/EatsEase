package com.example.eatsease.login_signup.mealdetail;

public class Ingredient {
    private String name;
    private int imageResId;

    public Ingredient(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
