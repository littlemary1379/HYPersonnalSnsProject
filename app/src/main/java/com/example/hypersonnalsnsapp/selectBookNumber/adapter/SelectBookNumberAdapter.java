package com.example.hypersonnalsnsapp.selectBookNumber.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectBookNumber.model.PhoneBook;
import com.example.hypersonnalsnsapp.selectSMSNumber.adapter.SelectSMSViewHolder;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectBookNumberAdapter extends RecyclerView.Adapter {

    private static final String TAG = "SelectBookNumberAdapter";

    private List<PhoneBook> phoneBookList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_phone_contact_list, parent, false);
        return new SelectBookNumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SelectBookNumberViewHolder selectBookNumberViewHolder = (SelectBookNumberViewHolder) holder;
        selectBookNumberViewHolder.phoneBook=phoneBookList.get(position);
        selectBookNumberViewHolder.updateView();

    }

    public void reload(List<PhoneBook> phoneBookList){
        this.phoneBookList.clear();
        this.phoneBookList.addAll(phoneBookList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return phoneBookList.size();
    }
}
