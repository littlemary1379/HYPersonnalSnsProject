package com.example.hypersonnalsnsapp.selectBookNumber.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.getBankAndAddress.GetBankAndAddressActivity;
import com.example.hypersonnalsnsapp.selectBookNumber.model.PhoneBook;
import com.example.hypersonnalsnsapp.util.ActivityUtil;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

public class SelectBookNumberViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "SelectBookNumberViewHol";

    private TextView textViewName;
    private TextView textViewPhoneNumber;
    private TextView textViewSelect;

    public PhoneBook phoneBook;

    public SelectBookNumberViewHolder(@NonNull View itemView) {
        super(itemView);

        findView();
        setListener();
    }

    private void findView(){
        textViewName=itemView.findViewById(R.id.textViewName);
        textViewPhoneNumber=itemView.findViewById(R.id.textViewPhoneNumber);
        textViewSelect=itemView.findViewById(R.id.textViewSelect);
    }

    private void setListener(){
        textViewSelect.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "textViewSelect 클릭");
            ActivityUtil.startActivityNoFinish(itemView.getContext(), GetBankAndAddressActivity.class);
        });
    }

    public void updateView(){
        textViewName.setText(phoneBook.getName());
        String phoneNum=phoneBook.getPhoneNumber();

        if(!phoneNum.contains("-")){
            if (phoneNum.length() == 10) {
                String address1 = phoneNum.substring(0, 3);
                String address2 = phoneNum.substring(3, 6);
                String address3 = phoneNum.substring(6, 10);
                textViewPhoneNumber.setText(address1 + "-" + address2 + "-" + address3);
            } else if (phoneNum.length() == 11) {
                String address1 = phoneNum.substring(0, 3);
                String address2 = phoneNum.substring(3, 7);
                String address3 = phoneNum.substring(7, 11);
                textViewPhoneNumber.setText(address1 + "-" + address2 + "-" + address3);
            }
        }else{
            textViewPhoneNumber.setText(phoneNum);
        }
    }
}
