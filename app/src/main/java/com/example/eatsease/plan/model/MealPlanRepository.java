package com.example.eatsease.plan.model;

import static okhttp3.internal.Internal.instance;

import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observer;
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





//    public void backupDataToFirestore(RepoCallback<String> callback) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (user != null) {
//            String userEmail = user.getEmail();
//
//            // Get the list of favorite meals from Room
//            getStoredFavMeals().observeForever(new Observer<List<FavMeal>>() {
//                @Override
//                public void onChanged(List<FavMeal> favMeals) {
//
//                    if (favMeals != null) {
//                        for (FavMeal favMeal : favMeals) {
//                            // Upload each meal to Firestore
//                            db.collection("users").document(userEmail)
//                                    .collection("favorites").document(favMeal.getId())
//                                    .set(favMeal)
//                                    .addOnSuccessListener(aVoid ->callback.onSuccess("Meal backed up successfully"))
//                                    .addOnFailureListener(e -> callback.onError(e));
//                        }
//                    }
//
//                }
//            });
//        }
//    }
//
//
//
//    public void restoreDataFromFirestore(RepoCallback<String> callback) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//
//
//        if (user != null) {
//            String userEmail = user.getEmail();
//
//            db.collection("users").document(userEmail)
//                    .collection("favorites").get()
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                FavMeal favMeal = document.toObject(FavMeal.class);
//                                insert(favMeal); // Insert into Room database
//                            }
//                            callback.onSuccess("Data restored successfully");
//                        } else {
//                            callback.onError(task.getException());
//                        }
//                    });
//        }
//    }


//    // Plans
//
//    public void backupPlanDataToFirestore(RepoCallback<String> callback) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (user != null) {
//            String userEmail = user.getEmail();
//
//            // Get the list of favorite meals from Room
//            Observer<List<MealPlan>> observer = new Observer<List<MealPlan>>() {
//                @Override
//                public void onChanged(List<MealPlan> plans) {
//                    if (plans != null) {
//                        for (MealPlan mealPlan : plans) {
//                            // Upload each meal to Firestore
//                            db.collection("users").document(userEmail)
//                                    .collection("Plans").document(String.valueOf(mealPlan.getId()))
//                                    .set(mealPlan)
//                                    .addOnSuccessListener(aVoid -> callback.onSuccess("Plan backed up successfully"))
//                                    .addOnFailureListener(e -> callback.onError(e));
//                        }
//                    }
//                    // Remove the observer after the backup is complete
//                    getStoredPlan().removeObserver(this);
//                }
//            };
//            getStoredPlan().observeForever(observer);
//        }
//    }
//
//
//    public void restorePlanDataFromFirestore(RepoCallback<String> callback) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//
//
//        if (user != null) {
//            String userEmail = user.getEmail();
//
//            db.collection("users").document(userEmail)
//                    .collection("Plans").get()
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Plan plan = document.toObject(Plan.class);
//                                insertPlan(plan); // Insert into Room database
//                            }
//                            callback.onSuccess("Data restored successfully");
//                        } else {
//                            callback.onError(task.getException());
//                        }
//                    });
//}
//}
}
