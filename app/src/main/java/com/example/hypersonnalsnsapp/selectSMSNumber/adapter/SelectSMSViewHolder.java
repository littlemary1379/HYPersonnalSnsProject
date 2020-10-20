package com.example.hypersonnalsnsapp.selectSMSNumber.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectSMSNumber.model.Message;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SelectSMSViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "SelectSMSViewHolder";

    private TextView textViewName;
    private TextView textViewContent;
    private TextView textViewDate;

    public Message msg;

    public SelectSMSViewHolder(@NonNull View itemView) {
        super(itemView);
        findView();
    }

    private void findView() {
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewContent = itemView.findViewById(R.id.textViewContent);
        textViewDate = itemView.findViewById(R.id.textViewDate);
    }

    public void updateView() {

        String address = msg.address;

        if (msg.address.equals("#CMAS#Severe") || address.length() < 10) {
            itemView.getLayoutParams().height = 0;
        }

        if (address.length() == 10) {
            String address1 = address.substring(0, 3);
            String address2 = address.substring(3, 6);
            String address3 = address.substring(6, 10);
            textViewName.setText(address1 + "-" + address2 + "-" + address3);
        } else if (address.length() == 11) {
            String address1 = address.substring(0, 3);
            String address2 = address.substring(3, 7);
            String address3 = address.substring(7, 11);
            textViewName.setText(address1 + "-" + address2 + "-" + address3);
        }

        textViewContent.setText(msg.body);

        DateFormat dateFormat = new SimpleDateFormat("MM월 dd일");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(msg.timeStamp);
        String finalDataString = dateFormat.format(calendar.getTime());
        textViewDate.setText(finalDataString);

    }
}
