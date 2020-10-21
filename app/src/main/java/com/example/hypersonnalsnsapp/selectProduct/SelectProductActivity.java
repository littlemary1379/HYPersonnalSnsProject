package com.example.hypersonnalsnsapp.selectProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

public class SelectProductActivity extends AppCompatActivity {

    private static final String TAG = "SelectProductActivity";

    //product1
    private TextView textViewProduct1OneCost;
    private TextView textViewProduct1AllCost;
    private EditText editTextProduct1Count;
    private ImageView imageViewProduct1Plus;
    private ImageView imageViewProduct1Minus;
    private int product1Count = 0;
    private int product1AllCost = 0;

    //product2
    private TextView textViewProduct2OneCost;
    private TextView textViewProduct2AllCost;
    private EditText editTextProduct2Count;
    private ImageView imageViewProduct2Plus;
    private ImageView imageViewProduct2Minus;
    private int product2Count = 0;
    private int product2AllCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);
        findView();
        setListener();
    }

    private void findView() {
        textViewProduct1OneCost = findViewById(R.id.textViewProduct1OneCost);
        textViewProduct1AllCost = findViewById(R.id.textViewProduct1AllCost);
        imageViewProduct1Plus = findViewById(R.id.imageViewProduct1Plus);
        imageViewProduct1Minus = findViewById(R.id.imageViewProduct1Minus);
        editTextProduct1Count = findViewById(R.id.editTextProduct1Count);
    }

    private void setListener() {

        //product1
        imageViewProduct1Plus.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "textViewProduct1Plus 클릭");
            String[] stringProduct1OneCost = textViewProduct1OneCost.getText().toString().split("원");
            int product1OneCost = Integer.valueOf(stringProduct1OneCost[0]);
            product1Count += 1;
            editTextProduct1Count.setText(product1Count + "");
            textViewProduct1AllCost.setText((product1OneCost * product1Count) + "원");
        });

        imageViewProduct1Minus.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "textViewProduct1Plus 클릭");
            String[] stringProduct1OneCost = textViewProduct1OneCost.getText().toString().split("원");
            int product1OneCost = Integer.valueOf(stringProduct1OneCost[0]);
            if(product1Count<1){
                product1Count=0;
            }else{
                product1Count -= 1;
            }
            editTextProduct1Count.setText(product1Count + "");
            textViewProduct1AllCost.setText((product1OneCost * product1Count) + "원");
        });

        editTextProduct1Count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextProduct1Count.getText().toString().equals("")) {
                    product1Count = 0;
                } else {
                    product1Count = Integer.valueOf(editTextProduct1Count.getText().toString());
                }
                String[] stringProduct1OneCost = textViewProduct1OneCost.getText().toString().split("원");
                int product1OneCost = Integer.valueOf(stringProduct1OneCost[0]);
                textViewProduct1AllCost.setText((product1OneCost * product1Count) + "원");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}