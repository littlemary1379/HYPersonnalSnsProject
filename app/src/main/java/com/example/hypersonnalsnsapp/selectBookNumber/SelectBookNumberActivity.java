package com.example.hypersonnalsnsapp.selectBookNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hypersonnalsnsapp.R;

public class SelectBookNumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book_number);
    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.in_left, R.anim.out_right);
        super.finish();
    }
}