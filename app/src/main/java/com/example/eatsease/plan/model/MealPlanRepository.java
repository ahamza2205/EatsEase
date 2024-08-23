package com.example.eatsease.plan.model;

import static okhttp3.internal.Internal.instance;

import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import okhttp3.internal.Internal;


public class MealPlanRepository {

    private static MealPlanRepository instance;
    private MealPlanDao mealPlanDao;
    private FirebaseAuth firebaseAuth;

    private MealPlanRepository(Context context) {
        MealPlanDatabase db = MealPlanDatabase.getInstance(context);
        mealPlanDao = db.mealPlanDao();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Singleton pattern to get the instance of the repository
    public static MealPlanRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (MealPlanRepository.class) {
                if (instance == null) {
                    instance = new MealPlanRepository(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public Single<List<MealPlan>> getMealPlanByDate(String date) {
        return mealPlanDao.getMealPlanByDate(date);
    }

    public Completable saveMealPlan(MealPlan mealPlan) {
        return mealPlanDao.insertMealPlan(mealPlan);
    }

    public Completable deleteAllMeals() {
        return mealPlanDao.deleteAllMeals();
    }

    public Completable deleteMealPlanByDate(String id) {
        return mealPlanDao.deleteMealPlanByDate(id);
    }
}
