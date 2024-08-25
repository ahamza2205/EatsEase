package com.example.eatsease.model.network.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AreaResponse {
    @SerializedName("meals")
    private List<Area> meals;

    public void setMeals(List<Area> meals) {
        this.meals = meals;
    }

    public List<Area> getMeals() {
        return meals;
    }

    public static class Area {
        @SerializedName("strArea")
        private String areaName;

        public Area(String areaName) {
            this.areaName = areaName;
        }
        public String getAreaName() {
            return areaName;
        }
    }
}
