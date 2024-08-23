package com.example.eatsease.favorite.view;

import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.response.CategoriesResponse;

import java.util.List;

public interface IFavMealView {
    void onFetchDataSuccess(List<FavoriteMeal> favoriteMeals);

    void onFetchDataError(String errorMessage);

}
