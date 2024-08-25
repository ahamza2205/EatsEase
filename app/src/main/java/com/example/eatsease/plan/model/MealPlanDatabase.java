package com.example.eatsease.plan.model;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MealPlan.class}, version = 1)
public abstract class MealPlanDatabase extends RoomDatabase {

    public abstract MealPlanDao mealPlanDao();

    private static MealPlanDatabase instance;

    public static synchronized MealPlanDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealPlanDatabase.class, "meal_plan_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
