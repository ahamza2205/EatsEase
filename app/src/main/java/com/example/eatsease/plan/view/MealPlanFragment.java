package com.example.eatsease.plan.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;  // Import Toast for error messages
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsease.R;
import com.example.eatsease.plan.MealPlanInterFaces;
import com.example.eatsease.plan.model.MealPlan;
import com.example.eatsease.plan.presenter.MealPlanPresenter;

import java.util.ArrayList;
import java.util.List;

public class MealPlanFragment extends Fragment implements MealPlanInterFaces.View {

    private RecyclerView recyclerView;
    private MealPlanAdapter adapter;
    private MealPlanPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender2, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up adapter
        adapter = new MealPlanAdapter(new ArrayList<>(), getContext(), mealPlan -> {
            // Handle delete action
            presenter.deleteMealPlan(mealPlan.getDate());
        });
        recyclerView.setAdapter(adapter);

        // Initialize presenter and load data
        presenter = new MealPlanPresenter(this, getContext());
        presenter.loadMealPlan("2024-08-23"); // Example date or selected date from calendar

        return view;
    }

    @Override
    public void showMealPlan(MealPlan mealPlan) {
        List<MealPlan> mealPlans = new ArrayList<>();
        mealPlans.add(mealPlan);  // Assuming single meal per date
        adapter.setMealPlans(mealPlans);
    }

    @Override
    public void showMealPlanError(String error) {
        // Display error message using a Toast or any other method
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMealPlanDeleted(String date) {
        // Handle meal deletion and update UI
        presenter.loadMealPlan(date);  // Reload the data after deletion
    }

    @Override
    public void showDeleteError(String error) {
        // Display delete error message using a Toast or any other method
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMealPlanSaved() {
        // Optionally, you might want to reload data or update the UI
        Toast.makeText(getContext(), "Meal plan saved successfully!", Toast.LENGTH_SHORT).show();
    }
}
