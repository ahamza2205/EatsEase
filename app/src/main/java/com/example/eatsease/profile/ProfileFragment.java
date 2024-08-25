package com.example.eatsease.profile;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.eatsease.R;
import com.example.eatsease.favorite.presenter.FavoriteMealPresenter;
import com.example.eatsease.favorite.view.IFavMealView;
import com.example.eatsease.login_signup.authentication.activity.login.LogIn;
import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.respiratory.Respiratory;
import com.example.eatsease.plan.MealPlanInterFaces;
import com.example.eatsease.plan.model.MealPlan;
import com.example.eatsease.plan.presenter.MealPlanPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView profileEmail;
    private MaterialButton logoutBtn;
    private FirebaseAuthManager authManager;
    private SharedPreRespiratory sharedPrefRespiratory;
    private Button backupBtn;
    private Button restoreBtn;

       FavoriteMealPresenter favoriteMealPresenter = new FavoriteMealPresenter(new Respiratory(getContext()), new IFavMealView() {

        @Override
        public void onFetchDataSuccess(List<FavoriteMeal> favoriteMeals) {

        }

        @Override
        public void onFetchDataError(String errorMessage) {

        }
    });
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authManager = FirebaseAuthManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileEmail = view.findViewById(R.id.profile_email);
        logoutBtn = view.findViewById(R.id.logout);
        backupBtn = view.findViewById(R.id.backup);
        restoreBtn = view.findViewById(R.id.restor);
        // Initialize SharedPreferences
        sharedPrefRespiratory = SharedPreRespiratory.getInstance(getActivity());

        // Get user email and display it
        FirebaseUser currentUser = authManager.getCurrentUser();
        if (currentUser != null) {
            // If user is logged in, display their email
            profileEmail.setText(currentUser.getEmail());
        } else {
            // If no user is logged in, display guest information
            // Optionally you could set a specific guest identifier or text
            profileEmail.setText("Guest");
        }

        // Handle logout button click
        logoutBtn.setOnClickListener(v -> {
            // Log out the user
            authManager.signOut();

            // Remove user details from SharedPreferences
            sharedPrefRespiratory.removeUserDetails();

            // Navigate back to the login activity
            Intent intent = new Intent(getActivity(), LogIn.class);
            startActivity(intent);
            getActivity().finish();
        });

        // Handle backup button click
        backupBtn.setOnClickListener(v -> {
           favoriteMealPresenter.backupFavoriteMeals();
           // mealPlanPresenter.backUpPlan("2022-01-01");
        });

        // Handle restore button click
        restoreBtn.setOnClickListener(v -> {
            favoriteMealPresenter.restoreFavoriteMeals();
        });

        return view;
    }
}
