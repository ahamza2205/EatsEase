package com.example.eatsease.home.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AreaResponse {
    @SerializedName("meals")
    private List<Area> meals;

    public List<Area> getMeals() {
        return meals;
    }

    public static class Area {
        @SerializedName("strArea")
        private String areaName;

        public String getAreaName() {
            return areaName;
        }
    }
}
