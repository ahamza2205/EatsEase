package com.example.eatsease.model.network.response;

import java.util.List;

public class IngredienttsResponse {
    private List<Ingredientt> meals;

    public List<Ingredientt> getMeals() {
        return meals;
    }

    public void setMeals(List<Ingredientt> meals) {
        this.meals = meals;
    }
}
