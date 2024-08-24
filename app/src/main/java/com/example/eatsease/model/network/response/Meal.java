package com.example.eatsease.model.network.response;

import com.example.eatsease.mealdetail.Ingredient;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Meal {
    @SerializedName("idMeal")
    private String mealId;

    @SerializedName("strMeal")
    private String mealName;

    @SerializedName("strDrinkAlternate")
    private String drinkAlternate;

    @SerializedName("strCategory")
    private String category;

    @SerializedName("strArea")
    private String area;

    @SerializedName("strInstructions")
    private String instructions;

    @SerializedName("strMealThumb")
    private String mealThumbnail;

    @SerializedName("strTags")
    private String tags;

    @SerializedName("strYoutube")
    private String youtubeUrl;

    @SerializedName("strIngredient1")
    private String ingredient1;

    @SerializedName("strIngredient2")
    private String ingredient2;

    @SerializedName("strIngredient3")
    private String ingredient3;

    @SerializedName("strIngredient4")
    private String ingredient4;

    @SerializedName("strIngredient5")
    private String ingredient5;

    @SerializedName("strIngredient6")
    private String ingredient6;

    @SerializedName("strIngredient7")
    private String ingredient7;

    @SerializedName("strIngredient8")
    private String ingredient8;

    @SerializedName("strIngredient9")
    private String ingredient9;

    @SerializedName("strIngredient10")
    private String ingredient10;

    @SerializedName("strMeasure1")
    private String measure1;

    @SerializedName("strMeasure2")
    private String measure2;

    @SerializedName("strMeasure3")
    private String measure3;

    @SerializedName("strMeasure4")
    private String measure4;

    @SerializedName("strMeasure5")
    private String measure5;

    @SerializedName("strMeasure6")
    private String measure6;

    @SerializedName("strMeasure7")
    private String measure7;

    @SerializedName("strMeasure8")
    private String measure8;

    @SerializedName("strMeasure9")
    private String measure9;

    @SerializedName("strMeasure10")
    private String measure10;

    @SerializedName("strSource")
    private String source;

    @SerializedName("strImageSource")
    private String imageSource;

    @SerializedName("strCreativeCommonsConfirmed")
    private String creativeCommonsConfirmed;

    @SerializedName("dateModified")
    private String dateModified;

    public Meal(String mealId) {
        this.mealId = mealId;
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getName().startsWith("ingredient")) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null && !value.isEmpty()) {
                        ingredients.add(new Ingredient(value));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return ingredients;
    }


    // Getters
    public String getMealId() {
        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public String getDrinkAlternate() {
        return drinkAlternate;
    }

    public String getCategory() {
        return category;
    }

    public String getArea() {
        return area;
    }

    public String getInstructions() {
        return instructions;
    }
    public String getMealThumbnail() {
        return mealThumbnail;
    }

    public String getTags() {
        return tags;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public String getMeasure1() {
        return measure1;
    }

    public String getMeasure2() {
        return measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public String getMeasure7() {
        return measure7;
    }

    public String getMeasure8() {
        return measure8;
    }

    public String getMeasure9() {
        return measure9;
    }

    public String getMeasure10() {
        return measure10;
    }

    public String getSource() {
        return source;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getCreativeCommonsConfirmed() {
        return creativeCommonsConfirmed;
    }

    public String getDateModified() {
        return dateModified;
    }
}
