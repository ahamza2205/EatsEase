package com.example.eatsease.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Delete;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import java.util.List;

@Dao
public interface FavoriteMealDao {

    // Insert a new favorite meal, replacing if it already exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(FavoriteMeal favoriteMeal);

    // Get all favorite meals for a particular user
    @Query("SELECT * FROM favorite_meals")
    Flowable<List<FavoriteMeal>> getAllFavoriteMeals();

    // Get all favorite meals for a particular user
    @Query("SELECT * FROM favorite_meals WHERE userEmail = :userEmail")
    Flowable<List<FavoriteMeal>> getAllFavoriteMealsByUserEmail(String userEmail);

    // Get a specific meal by mealId
    @Query("SELECT * FROM favorite_meals WHERE mealId = :mealId LIMIT 1")
    Flowable<FavoriteMeal> getMealById(String mealId);

    // Delete a favorite meal
    @Delete
    Completable delete(FavoriteMeal favoriteMeal);
}
