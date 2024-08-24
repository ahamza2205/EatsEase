package com.example.eatsease.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eatsease.R;
import com.example.eatsease.login_signup.authentication.activity.login.LogIn;
import com.example.eatsease.login_signup.authentication.model.auth_manager.FirebaseAuthManager;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private TextView profileEmail;
    private MaterialButton logoutBtn;
    private FirebaseAuthManager authManager;
    private SharedPreRespiratory sharedPrefRespiratory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authManager = new FirebaseAuthManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileEmail = view.findViewById(R.id.profile_email); // Correct ID
        logoutBtn = view.findViewById(R.id.logout); // Correct ID

        // Get user email and display it
        FirebaseUser currentUser = authManager.getCurrentUser();
        if (currentUser != null) {
            profileEmail.setText(currentUser.getEmail());
        }

        // Initialize SharedPreferences
        sharedPrefRespiratory = SharedPreRespiratory.getInstance(getActivity());

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

        return view;
    }
}
