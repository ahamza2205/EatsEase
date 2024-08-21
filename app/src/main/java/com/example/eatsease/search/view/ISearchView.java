package com.example.eatsease.search.view;

import com.example.eatsease.model.network.response.AreaResponse;
import com.example.eatsease.model.network.response.CategoriesResponse;

import java.util.List;

public interface ISearchView {
    void onFetchCategoriesSuccess(CategoriesResponse categoriesResponse);
    void onFetchCategoriesError(Throwable throwable);
    void onFetchAreasSuccess(List<AreaResponse.Area> areas);  // Correct method signature
    void onFetchAreasError(Throwable throwable);
}
