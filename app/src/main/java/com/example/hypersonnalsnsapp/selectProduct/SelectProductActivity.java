package com.example.hypersonnalsnsapp.selectProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectProduct.adapter.SelectProductAdapter;
import com.example.hypersonnalsnsapp.selectProduct.adapter.SelectProductViewHolder;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

public class SelectProductActivity extends AppCompatActivity {

    private static final String TAG = "SelectProductActivity";

    private RecyclerView recyclerView;

    private SelectProductAdapter selectProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);
        findView();
        init();
    }



    private void findView() {
        recyclerView = findViewById(R.id.recyclerview);
    }

    private void init(){
        selectProductAdapter = new SelectProductAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(selectProductAdapter);
    }

}