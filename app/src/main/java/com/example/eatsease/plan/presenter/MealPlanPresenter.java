package com.example.eatsease.plan.presenter;

import android.content.Context;
import android.util.Log;

import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.plan.MealPlanInterFaces;
import com.example.eatsease.plan.model.MealPlan;
import com.example.eatsease.plan.view.MealPlanModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public void backUpPlan(String date) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userEmail = user.getEmail();
            Log.d("MealPlanPresenter", "Backing up plans for date: " + date + ", user: " + userEmail);

            model.getMealPlan(date, userEmail)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            mealPlans -> {
                                if (!mealPlans.isEmpty()) {
                                    DocumentReference userDocRef = db.collection("users").document(userEmail);

                                    for (MealPlan plan : mealPlans) {
                                        userDocRef.collection("Plans")
                                                .document(plan.getMealId())
                                                .set(plan)
                                                .addOnSuccessListener(aVoid -> Log.d("Firestore", "Meal Plan backed up successfully: " + plan.getMealId()))
                                                .addOnFailureListener(e -> {
                                                    Log.e("Firestore", "Error backing up meal: " + plan.getMealId(), e);
                                                    view.showMealPlanError("Failed to back up meal: " + plan.getMealName());
                                                });
                                    }
                                } else {
                                    Log.w("MealPlanPresenter", "No meal plans found for backup on date: " + date);
                                    view.showMealPlanError("No meal plans found for the selected date.");
                                }
                            },
                            throwable -> {
                                Log.e("MealPlanPresenter", "Error fetching meal plans for backup: " + throwable.getMessage());
                                view.showMealPlanError("Failed to back up meal plans.");
                            }
                    );
        } else {
            view.showMealPlanError("User not authenticated.");
        }
    }

    public void restorePlan(String date) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userEmail = user.getEmail();
            Log.d("MealPlanPresenter", "Restoring plans for date: " + date + ", user: " + userEmail);

            DocumentReference userDocRef = db.collection("users").document(userEmail);

            userDocRef.collection("Plans")
                    .whereEqualTo("date", date)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (DocumentSnapshot document : queryDocumentSnapshots) {
                                MealPlan mealPlan = document.toObject(MealPlan.class);
                                Log.d("Firestore", "Restoring meal plan: " + mealPlan.getMealId());

                                model.saveMealPlan(mealPlan)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                () -> Log.d("Firestore", "Meal Plan restored and saved locally: " + mealPlan.getMealId()),
                                                throwable -> {
                                                    Log.e("Firestore", "Error saving restored meal plan: " + mealPlan.getMealId(), throwable);
                                                    view.showMealPlanError("Failed to save restored meal: " + mealPlan.getMealName());
                                                }
                                        );
                            }
                            view.showMealPlanSaved();
                        } else {
                            Log.w("MealPlanPresenter", "No meal plans found for restoration on date: " + date);
                            view.showMealPlanError("No meal plans found for the selected date.");
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firestore", "Error fetching meal plans for restoration.", e);
                        view.showMealPlanError("Failed to restore meal plans.");
                    });
        } else {
            view.showMealPlanError("User not authenticated.");
        }
    }


}
