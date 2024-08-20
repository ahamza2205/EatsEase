package com.example.eatsease.home.model.response;

import java.util.ArrayList;

public class CategoriesResponse {
    public ArrayList<CategoryResponse> categories;

    public CategoriesResponse() {
        categories = new ArrayList<>();
    }

    public CategoriesResponse(ArrayList<CategoryResponse> categories) {
        this.categories = categories;
    }

    public ArrayList<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryResponse> categories) {
        this.categories = categories;
    }
}