package com.example.eatsease.favorite.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsease.R;
import com.example.eatsease.favorite.presenter.FavoriteMealPresenter;
import com.example.eatsease.home.view.fragment.adapter.Recipe.RecipeAdapter;
import com.example.eatsease.login_signup.authentication.model.sharedperferences.SharedPreRespiratory;
import com.example.eatsease.model.database.FavoriteMeal;
import com.example.eatsease.model.network.response.Meal;
import com.example.eatsease.model.respiratory.Respiratory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements IFavMealView {

    private FavoriteMealsAdapter adapter;
    private FavoriteMealPresenter presenter;
    private RecipeAdapter.OnRecipeClickListener onRecipeClickListener;
    private SharedPreRespiratory sharedPreRespiratory = SharedPreRespiratory.getInstance(getContext());
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
        String userEmail = sharedPreRespiratory.getUserEmail();
        if (userEmail == null || userEmail.isEmpty()) {
            // Handle the case where user email is not available
            Toast.makeText(getContext(), "User email is not available", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.fetchFavoriteMealsbyUserEmail(userEmail);
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
