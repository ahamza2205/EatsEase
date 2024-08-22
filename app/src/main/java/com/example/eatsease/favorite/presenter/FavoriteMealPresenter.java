//package com.example.eatsease.favorite.presenter;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import androidx.core.content.ContextCompat;
//
//import com.example.eatsease.R;
//import com.example.eatsease.favorite.view.IFavMealView;
//import com.example.eatsease.model.database.FavoriteMeal;
//
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.schedulers.Schedulers;
//
//public class FavoriteMealPresenter {
//    private Repository repository;
//    private IFavMealView iFavMealView;
//    private Context context;
//
//    public FavoriteMealPresenter(IFavMealView iFavMealView, Context context) {
//        this.iFavMealView = iFavMealView;
//        this.context = context;
//        this.repository = new Repository(context);
//    }
//
////    public void handleFavoriteAction(FavoriteMeal favMeal, android.widget.ImageView favIcon) {
////        repository.isFavoriteMeal(favMeal.getMealId())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(isFavorite -> {
////                    if (isFavorite) {
////                        // Remove from favorites
////                        repository.removeFavoriteMeal(favMeal)
////                                .subscribeOn(Schedulers.io())
////                                .observeOn(AndroidSchedulers.mainThread())
////                                .subscribe(() -> {
////                                    favIcon.setColorFilter(ContextCompat.getColor(context, R.color.white)); // Reset color
////                                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
////                                });
////                    } else {
////                        // Add to favorites
////                        repository.addFavoriteMeal(favMeal)
////                                .subscribeOn(Schedulers.io())
////                                .observeOn(AndroidSchedulers.mainThread())
////                                .subscribe(() -> {
////                                    favIcon.setColorFilter(ContextCompat.getColor(context, R.color.red)); // Change to red
////                                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
////                                });
////                    }
////                });
////    }
//}
