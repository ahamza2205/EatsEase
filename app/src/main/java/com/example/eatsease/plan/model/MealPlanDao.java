package com.example.eatsease.plan.model;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMealPlan(MealPlan mealPlan);

    @Query("DELETE FROM mealPlan")
    Completable deleteAllMeals();

    @Query("SELECT * FROM mealPlan WHERE date = :date ")
    Single<List<MealPlan>> getMealPlanByDate(String date);

    @Query("DELETE FROM mealPlan WHERE mealId = :id")
    Completable deleteMealPlanByDate(String id);

    @Query("SELECT * FROM mealPlan WHERE date = :date AND userEmail = :userEmail")
    Single<List<MealPlan>> getMealPlanByDateAndUserEmail(String date, String userEmail);

    @Query("DELETE FROM mealPlan WHERE mealId = :id AND userEmail = :userEmail")
    Completable deleteMealPlanByIdAndUserEmail(int id, String userEmail);
}
