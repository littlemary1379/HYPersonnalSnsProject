package com.example.hypersonnalsnsapp.getBankAndAddress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;

public class GetBankAndAddressActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String TAG = "GetBankAndAddressActivi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_bank_and_address);
        findView();
        initSpinner();
    }

    private void findView(){
        spinner = findViewById(R.id.spinner);
    }

    private void initSpinner(){
        String[] items = getResources().getStringArray(R.array.bank_array);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items);
        
        spinner.setAdapter(arrayAdapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DebugLogUtil.logD(TAG, "item 클릭");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}