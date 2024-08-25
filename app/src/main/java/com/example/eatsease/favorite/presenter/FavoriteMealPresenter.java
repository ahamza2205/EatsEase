package com.example.eatsease.favorite.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.database.FavoriteMealDao;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.favorite.view.IFavMealView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteMealPresenter {
    private Respiratory repository;
    private IFavMealView view;
    private FavoriteMeal favoriteMeal;

    public FavoriteMealPresenter(Respiratory repository, IFavMealView view) {
        this.repository = repository;
        this.view = view;
    }

    public void fetchFavoriteMealsbyUserEmail(String userEmail) {
        repository.getFavoriteMealsByUserEmail(userEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(favoriteMeals -> {
                    Log.d("FavoriteMeals", "Fetched meals count: " + favoriteMeals.size());
                    view.onFetchDataSuccess(favoriteMeals);
                }, throwable -> {
                    Log.e("FavoriteMeals", "Error fetching favorite meals: " + throwable.getMessage());
                });
    }


    public void deleteFavoriteMeal(FavoriteMeal favoriteMeal) {
        repository.removeFavoriteMeal(favoriteMeal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Log.d("FavoriteFragment", "Meal deleted successfully"),
                        throwable -> Log.e("FavoriteFragment", "Error deleting meal: " + throwable.getMessage())
                );
    }


    public void backupFavoriteMeals() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Assuming you have a list of FavMeals
       // List<FavoriteMeal> favMeals = getAllFavoriteMeals(); // Replace with your actual method

        // Get current user email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null) {

            String userEmail = user.getEmail();

            repository.getFavoriteMeals()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            favoriteMeals -> {
                                DocumentReference userDocRef = db.collection("users").document(userEmail);

                                // Backup favorite meals
                                for (FavoriteMeal favMeal : favoriteMeals) {
                                    userDocRef.collection("favorites").document(favMeal.getMealId()).set(favMeal)
                                            .addOnSuccessListener(aVoid -> Log.d("Firestore", "Favorite Meal backed up successfully"))
                                            .addOnFailureListener(e -> Log.w("Firestore", "Error backing up meal", e));
                                }
                            },
                            throwable -> {
                                Log.e("FavoriteFragment", "Error fetching meals: " + throwable.getMessage());
                                view.onFetchDataError(throwable.getMessage());
                            }
                    );


            // Similarly, backup meal plans or other data
        }
    }


public void restoreFavoriteMeals() {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Get current user email
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if (user!= null)

    {
        String userEmail = user.getEmail();

        DocumentReference userDocRef = db.collection("users").document(userEmail);
        // Restore favorite meals
        userDocRef.collection("favorites").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    FavoriteMeal favMeal = document.toObject(FavoriteMeal.class);
                    repository.addFavoriteMeal(favMeal)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    () -> {

                                        Log.d("Firestore", "restoreFavoriteMeals: ");
                                    },
                                    throwable -> {
                                        // Handle error
                                        throwable.printStackTrace();
                                    });

                }
            } else {
                Log.w("Firestore", "Error getting documents.", task.getException());
            }
        });


    }
}

}
