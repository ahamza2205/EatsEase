package com.example.eatsease.plan.view;

import android.content.Context;

import com.example.eatsease.plan.MealPlanInterFaces;
import com.example.eatsease.plan.model.MealPlan;
import com.example.eatsease.plan.model.MealPlanRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealPlanModel implements MealPlanInterFaces.Model {
    private MealPlanRepository mealPlanRepository;

    public MealPlanModel(Context context) {
        mealPlanRepository = MealPlanRepository.getInstance(context);
    }


    @Override
    public Single<List<MealPlan>> getMealPlan(String date) {
        return mealPlanRepository.getMealPlanByDate(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable saveMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.saveMealPlan(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deleteMealPlan(String id) {
        return mealPlanRepository.deleteMealPlanByDate(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
