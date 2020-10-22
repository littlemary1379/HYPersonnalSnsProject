package com.example.hypersonnalsnsapp.selectProduct.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.util.Constant;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectProductAdapter extends RecyclerView.Adapter {

    private static final String TAG = "SelectProductAdapter";

    private List<String> productList= Constant.productList;
    private List<String> productOneCostList=Constant.productOneCostList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_product_list, parent, false);
        return new SelectProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SelectProductViewHolder selectProductViewHolder = (SelectProductViewHolder) holder;
        selectProductViewHolder.productName=productList.get(position);
        selectProductViewHolder.productOneCost=productOneCostList.get(position);
        selectProductViewHolder.updateView();

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
