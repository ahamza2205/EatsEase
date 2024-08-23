package com.example.eatsease.favorite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsease.R;
import com.example.eatsease.favorite.presenter.FavoriteMealPresenter;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.respiratory.Respiratory;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements IFavMealView {

    private FavoriteMealsAdapter adapter;
    private FavoriteMealPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        // Initialize the presenter
        presenter = new FavoriteMealPresenter(new Respiratory(getContext()), this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite2, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycleSearch);

        // Initialize adapter and pass the presenter
        adapter = new FavoriteMealsAdapter(new ArrayList<>(), getContext(), presenter);
       // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Fetch favorite meals
        presenter.fetchFavoriteMeals();
    }


    @Override
    public void onFetchDataSuccess(List<FavoriteMeal> favoriteMeals) {
        // Update the adapter with the fetched data
        adapter.setFavoriteMeal(favoriteMeals);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onFetchDataError(String errorMessage) {
        // Handle errors (e.g., show a Toast)
        Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }
}
