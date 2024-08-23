package com.example.eatsease.plan;

import com.example.eatsease.plan.model.MealPlan;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface MealPlanInterFaces {
    interface View {
        void showMealPlan(MealPlan mealPlan);
        void showMealPlanError(String error);
        void showMealPlanDeleted(String date);
        void showDeleteError(String error);
        void showMealPlanSaved();  // Added method
    }
    interface Presenter {
        void loadMealPlan(String date);
        void saveMealPlan(MealPlan mealPlan);
        void deleteMealPlan(String date);
    }
    interface Model {
        Single<MealPlan> getMealPlan(String date);  // Changed to Single
        Completable saveMealPlan(MealPlan mealPlan);  // Changed to Completable
        Completable deleteMealPlan(String date);  // Changed to Completable
    }
}
