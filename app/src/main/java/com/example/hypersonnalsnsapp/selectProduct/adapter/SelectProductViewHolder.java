package com.example.hypersonnalsnsapp.selectProduct.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

public class SelectProductViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "SelectProductViewHolder";

    //product1
    private TextView textViewProductOneCost;
    private TextView textViewProductAllCost;
    private EditText editTextProductCount;
    private ImageView imageViewProductPlus;
    private ImageView imageViewProductMinus;
    private TextView textViewProductName;
    public String productName;
    public String productOneCost;
    private int productCount = 0;
    private int productAllCost = 0;

    public SelectProductViewHolder(@NonNull View itemView) {
        super(itemView);
        findView();
        setListener();
    }

    private void findView(){
        //product
        textViewProductName=itemView.findViewById(R.id.textViewProductName);
        textViewProductOneCost = itemView.findViewById(R.id.textViewProductOneCost);
        textViewProductAllCost = itemView.findViewById(R.id.textViewProductAllCost);
        imageViewProductPlus = itemView.findViewById(R.id.imageViewProductPlus);
        imageViewProductMinus = itemView.findViewById(R.id.imageViewProductMinus);
        editTextProductCount = itemView.findViewById(R.id.editTextProductCount);
    }

    public void updateView(){
        textViewProductName.setText(productName);
        textViewProductOneCost.setText(productOneCost);
    }

    private void setListener() {

        //product1
        imageViewProductPlus.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "textViewProduct1Plus 클릭");
            String[] stringProduct1OneCost = textViewProductOneCost.getText().toString().split("원");
            int product1OneCost = Integer.valueOf(stringProduct1OneCost[0]);
            productCount += 1;
            editTextProductCount.setText(productCount + "");
            textViewProductAllCost.setText((product1OneCost * productCount) + "원");
        });

        imageViewProductMinus.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "textViewProduct1Plus 클릭");
            String[] stringProduct1OneCost = textViewProductOneCost.getText().toString().split("원");
            int product1OneCost = Integer.valueOf(stringProduct1OneCost[0]);
            if(productCount<1){
                productCount=0;
            }else{
                productCount -= 1;
            }
            editTextProductCount.setText(productCount + "");
            textViewProductAllCost.setText((product1OneCost * productCount) + "원");
        });

        editTextProductCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextProductCount.getText().toString().equals("")) {
                    productCount = 0;
                } else {
                    productCount = Integer.valueOf(editTextProductCount.getText().toString());
                }
                String[] stringProduct1OneCost = textViewProductOneCost.getText().toString().split("원");
                int product1OneCost = Integer.valueOf(stringProduct1OneCost[0]);
                textViewProductAllCost.setText((product1OneCost * productCount) + "원");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
