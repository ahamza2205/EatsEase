package com.example.eatsease.plan.model;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealPlanDao {

    @Insert
    Completable insertMealPlan(MealPlan mealPlan);

    @Query("SELECT * FROM mealPlan WHERE date = :date LIMIT 1")
    Single<MealPlan> getMealPlanByDate(String date);

    @Query("DELETE FROM mealPlan WHERE date = :date")
    Completable deleteMealPlanByDate(String date);
}
