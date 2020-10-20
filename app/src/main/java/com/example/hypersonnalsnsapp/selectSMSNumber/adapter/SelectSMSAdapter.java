package com.example.hypersonnalsnsapp.selectSMSNumber.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectSMSNumber.model.Message;

import java.util.ArrayList;
import java.util.List;

public class SelectSMSAdapter extends RecyclerView.Adapter {

    public List<Message> msgList=new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_sms_list,parent,false);
        return new SelectSMSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SelectSMSViewHolder selectSMSViewHolder = (SelectSMSViewHolder) holder;
        selectSMSViewHolder.msg=msgList.get(position);
        selectSMSViewHolder.updateView();
    }

    public void loadMsg(List<Message> msgList){
        this.msgList.clear();
        this.msgList.addAll(msgList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
