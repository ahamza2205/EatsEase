package com.example.eatsease.plan;

import com.example.eatsease.plan.model.MealPlan;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface MealPlanInterFaces {
    interface View {
        void showMealPlan(List<MealPlan> mealPlan);
        void showMealPlanError(String error);
        void showMealPlanDeleted(String date);
        void showDeleteError(String error);
        void showMealPlanSaved();  // Added method
    }
    interface Presenter {
        void loadMealPlan(String date);
        void saveMealPlan(MealPlan mealPlan);
        void deleteMealPlan(String id,String date);
    }
    interface Model {
        Single<List<MealPlan>> getMealPlan(String date);  // Changed to Single
        Completable saveMealPlan(MealPlan mealPlan);  // Changed to Completable
        Completable deleteMealPlan(String date);  // Changed to Completable
    }
}
