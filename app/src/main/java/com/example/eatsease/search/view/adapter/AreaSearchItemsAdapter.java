package com.example.eatsease.search.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatsease.R;
import com.example.eatsease.home.model.response.AreaResponse;

import java.util.List;

public class AreaSearchItemsAdapter extends RecyclerView.Adapter<AreaSearchItemsAdapter.SearchAdapter> {
    private List<AreaResponse.Area> areaList;
    private Context context;

    public AreaSearchItemsAdapter(List<AreaResponse.Area> areaList, Context context) {
        this.areaList = areaList;
        this.context = context;
    }

    public void setAreaList(List<AreaResponse.Area> areaList) {
        this.areaList = areaList;
        notifyDataSetChanged();  // Notify the adapter of data changes
    }

    @NonNull
    @Override
    public SearchAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_items, parent, false);
        return new SearchAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter holder, int position) {
        AreaResponse.Area area = areaList.get(position);
        holder.areaName.setText(area.getAreaName());
        // If you have an image, you can set it here
        holder.areaImage.setImageResource(R.drawable.foods);
    }

    @Override
    public int getItemCount() {
        return areaList != null ? areaList.size() : 0;
    }

    public static class SearchAdapter extends RecyclerView.ViewHolder {
        TextView areaName;
        ImageView areaImage;

        public SearchAdapter(@NonNull View itemView) {
            super(itemView);
            areaName = itemView.findViewById(R.id.searchcategoryName);
            areaImage = itemView.findViewById(R.id.searchcategoryImage);
        }
    }
}
