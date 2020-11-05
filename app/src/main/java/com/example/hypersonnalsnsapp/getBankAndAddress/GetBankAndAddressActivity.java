package com.example.hypersonnalsnsapp.getBankAndAddress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectProduct.SelectProductActivity;
import com.example.hypersonnalsnsapp.util.ActivityUtil;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;
import com.example.hypersonnalsnsapp.util.SharedPreferenceUtil;

public class GetBankAndAddressActivity extends AppCompatActivity {

    private Spinner spinner;
    private static final String TAG = "GetBankAndAddressActivi";
    private TextView textViewNextButton;
    private EditText editTextAccountName;
    private EditText editTextAccount;

    private String bank="";
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_bank_and_address);
        checkBundle();
        findView();
        initSpinner();
        setListener();
    }

    private void checkBundle() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        phone = bundle.getString("phone");
    }

    private void findView(){
        spinner = findViewById(R.id.spinner);
        textViewNextButton = findViewById(R.id.textViewNextButton);
        editTextAccountName = findViewById(R.id.editTextAccountName);
        editTextAccount = findViewById(R.id.editTextAccount);
    }

    private void initSpinner(){
        String[] items = getResources().getStringArray(R.array.bank_array);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items);
        
        spinner.setAdapter(arrayAdapter);
        
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DebugLogUtil.logD(TAG, "item 클릭");
                bank=items[position];
                DebugLogUtil.logD(TAG,bank);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setListener(){
        textViewNextButton.setOnClickListener(v -> {
            DebugLogUtil.logD(TAG, "textViewNextButton 클릭");

            String account=editTextAccount.getText().toString().trim();
            String accountName=editTextAccountName.getText().toString().trim();
            if(accountName.contains(" ")){
                accountName.replace(" ","");
            }

            if(!account.contains("-")) {
                Toast.makeText(this, "'-'를 넣어 계좌를 올바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if(accountName.trim().equals("")){
                Toast.makeText(this, "이름을 비울 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog.Builder alertBuilder= new AlertDialog.Builder(GetBankAndAddressActivity.this);

            alertBuilder.setTitle(R.string.getBank_dialogTitle);
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.getBank_dialogMessage));
            sb.append("\n\n" + "은행명 : "+bank);
            sb.append("\n" + "계좌번호 : "+ account);
            alertBuilder.setMessage(sb.toString());

            alertBuilder.setPositiveButton("네", (dialog, which) -> {
                DebugLogUtil.logD(TAG, "확인 버튼 클릭");
                SharedPreferenceUtil.registeredSharedPreference(GetBankAndAddressActivity.this, "bank", bank);
                SharedPreferenceUtil.registeredSharedPreference(GetBankAndAddressActivity.this, "account", account);

                Bundle bundle = new Bundle();
                bundle.putString("phone", phone);

                ActivityUtil.startActivityNoFinish(GetBankAndAddressActivity.this, SelectProductActivity.class, bundle);
            });

            alertBuilder.setNegativeButton("아니오", (dialog, which) -> DebugLogUtil.logD(TAG, "아니오 버튼 클릭"));

            AlertDialog alertDialog = alertBuilder.show();
            TextView messageText = alertDialog.findViewById(android.R.id.message);
            messageText.setTextSize(18);
            alertDialog.show();

        });

    }
}