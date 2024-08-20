package com.example.eatsease.search.view;

import com.example.eatsease.home.model.response.AreaResponse;
import com.example.eatsease.home.model.response.CategoriesResponse;

import java.util.List;

public interface ISearchView {
    void onFetchCategoriesSuccess(CategoriesResponse categoriesResponse);
    void onFetchCategoriesError(Throwable throwable);
    void onFetchAreasSuccess(List<AreaResponse.Area> areas);  // Correct method signature
    void onFetchAreasError(Throwable throwable);
}
