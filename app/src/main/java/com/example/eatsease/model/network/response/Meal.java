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


    // Constructors
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

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setDrinkAlternate(String drinkAlternate) {
        this.drinkAlternate = drinkAlternate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setMealThumbnail(String mealThumbnail) {
        this.mealThumbnail = mealThumbnail;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }

    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }

    public void setMeasure7(String measure7) {
        this.measure7 = measure7;
    }

    public void setMeasure8(String measure8) {
        this.measure8 = measure8;
    }

    public void setMeasure9(String measure9) {
        this.measure9 = measure9;
    }

    public void setMeasure10(String measure10) {
        this.measure10 = measure10;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public void setCreativeCommonsConfirmed(String creativeCommonsConfirmed) {
        this.creativeCommonsConfirmed = creativeCommonsConfirmed;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
