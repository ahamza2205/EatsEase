package com.example.eatsease.plan.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MealPlanFragment extends Fragment implements MealPlanInterFaces.View {

    private RecyclerView recyclerView;
    private MealPlanAdapter adapter;
    private MealPlanPresenter presenter;
    private CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender2, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();
        long todayMillis = calendar.getTimeInMillis();

        // Set the calendar to today's date
        calendarView.setDate(todayMillis, true, true);
        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up adapter
        adapter = new MealPlanAdapter(new ArrayList<>(), getContext(), mealPlan -> {
            // Handle delete action
            presenter.deleteMealPlan(mealPlan.getMealId(),mealPlan.getDate());
        });
        recyclerView.setAdapter(adapter);

        // Initialize presenter and load data
        presenter = new MealPlanPresenter(this, getContext());
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) ->
        {
            month = month + 1;

            presenter.loadMealPlan(convertDateFormat(year + "-" + month + "-" + dayOfMonth));
        });

        return view;
    }

    public String convertDateFormat(String dateStr)  {
        // Create SimpleDateFormat object for the input format
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-M-d", Locale.getDefault());

        // Parse the input date string into a Date object
        Date date = null;
        try {
            date = inputFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Create SimpleDateFormat object for the desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Format the Date object into the desired date string
        return outputFormat.format(date);
    }

    @Override
    public void showMealPlan(List<MealPlan> mealPlan) {
        adapter.setMealPlans(mealPlan);
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
