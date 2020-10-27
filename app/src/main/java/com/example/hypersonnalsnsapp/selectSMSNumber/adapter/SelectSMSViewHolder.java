package com.example.hypersonnalsnsapp.selectSMSNumber.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.getBankAndAddress.GetBankAndAddressActivity;
import com.example.hypersonnalsnsapp.selectProduct.SelectProductActivity;
import com.example.hypersonnalsnsapp.selectSMSNumber.model.Message;
import com.example.hypersonnalsnsapp.util.ActivityUtil;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;
import com.example.hypersonnalsnsapp.util.SharedPreferenceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SelectSMSViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "SelectSMSViewHolder";

    private TextView textViewName;
    private TextView textViewContent;
    private TextView textViewDate;
    private TextView textViewSelect;

    public Message msg;

    public SelectSMSViewHolder(@NonNull View itemView) {
        super(itemView);
        findView();
        setListener();
    }

    private void findView() {
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewContent = itemView.findViewById(R.id.textViewContent);
        textViewDate = itemView.findViewById(R.id.textViewDate);
        textViewSelect = itemView.findViewById(R.id.textViewSelect);
    }

    private void setListener() {
        textViewSelect.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "textViewSelect 클릭");
            SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences(SharedPreferenceUtil.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
            String bank = sharedPreferences.getString("bank", "");
            String account = sharedPreferences.getString("account", "");

            if (bank.equals("") || account.equals("")) {
                ActivityUtil.startActivityNoFinish(itemView.getContext(), GetBankAndAddressActivity.class);
            } else {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(itemView.getContext());

                alertBuilder.setTitle(R.string.getBank_dialogTitle);
                StringBuilder sb = new StringBuilder();
                sb.append(itemView.getContext().getString(R.string.getBank_dialogMessage));
                sb.append("\n\n" + "은행명 : " + bank);
                sb.append("\n" + "계좌번호 : " + account);
                alertBuilder.setMessage(sb.toString());

                alertBuilder.setPositiveButton("네", (dialog, which) -> {
                    DebugLogUtil.logD(TAG, "확인 버튼 클릭");
                    SharedPreferenceUtil.registeredSharedPreference(itemView.getContext(), "bank", bank);
                    SharedPreferenceUtil.registeredSharedPreference(itemView.getContext(), "account", account);
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", msg.address);
                    ActivityUtil.startActivityNoFinish(itemView.getContext(), SelectProductActivity.class, bundle);
                });

                alertBuilder.setNegativeButton("아니오", (dialog, which) -> {
                    DebugLogUtil.logD(TAG, "아니오 버튼 클릭");
                    ActivityUtil.startActivityNoFinish(itemView.getContext(), GetBankAndAddressActivity.class);
                });

                AlertDialog alertDialog = alertBuilder.show();
                TextView messageText = alertDialog.findViewById(android.R.id.message);
                messageText.setTextSize(18);
                alertDialog.show();

            }
        });
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
