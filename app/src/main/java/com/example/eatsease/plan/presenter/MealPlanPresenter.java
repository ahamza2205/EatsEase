package com.example.eatsease.plan.presenter;

import android.content.Context;

import com.example.eatsease.plan.MealPlanInterFaces;
import com.example.eatsease.plan.model.MealPlan;
import com.example.eatsease.plan.view.MealPlanModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealPlanPresenter implements MealPlanInterFaces.Presenter {
    private MealPlanInterFaces.View view;
    private MealPlanInterFaces.Model model;

    public MealPlanPresenter(MealPlanInterFaces.View view, Context context) {
        this.view = view;
        this.model = new MealPlanModel(context);
    }

    @Override
    public void loadMealPlan(String date) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            view.showMealPlanError("User not authenticated.");
            return;
        }
        String userEmail = user.getEmail();

        model.getMealPlan(date, userEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealPlan -> view.showMealPlan(mealPlan),
                        throwable -> view.showMealPlanError("No meal plan found.")
                );
    }


    @Override
    public void saveMealPlan(MealPlan mealPlan) {
        model.saveMealPlan(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showMealPlanSaved(),  // Notify View of success
                        throwable -> view.showMealPlanError("Failed to save meal plan.")  // Notify View of failure
                );
    }

    @Override
    public void deleteMealPlan(String id,String date) {
        model.deleteMealPlan(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showMealPlanDeleted(date),  // Notify View of success
                        throwable -> view.showMealPlanError("Failed to delete meal plan.")  // Notify View of failure
                );
    }





}
