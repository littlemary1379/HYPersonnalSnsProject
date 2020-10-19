package com.example.hypersonnalsnsapp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.selectSMSNumber.SelectSmsActivity;
import com.example.hypersonnalsnsapp.util.ActivityUtil;
import com.example.hypersonnalsnsapp.util.CheckPermissionUtil;
import com.example.hypersonnalsnsapp.util.Constant;
import com.example.hypersonnalsnsapp.util.DebugLogUtil;
import com.example.hypersonnalsnsapp.util.SharedPreferenceUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText editTextPhoneNumber;
    private TextView textViewNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        setListener();
        CheckPermissionUtil.checkPermission(MainActivity.this, Constant.manifest_permission_Read_SMS);

    }

    private void findView() {
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        textViewNextButton = findViewById(R.id.textViewNextButton);
    }

    private void setListener() {

        textViewNextButton.setOnClickListener(v -> {

                hideKeypad();

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String phoneNumber=editTextPhoneNumber.getText().toString();

                    if(phoneNumber.contains("-")){
                        phoneNumber.replace("-","");
                    }

                    if(phoneNumber.contains("/")){
                        phoneNumber.replace("/","");
                    }

                    if(phoneNumber.contains(" ")){
                        phoneNumber.replace(" ","");
                        phoneNumber.trim();
                    }

                    if(phoneNumber.length()<11){
                        DebugLogUtil.logD(TAG, "비어있음");
                        Toast.makeText(this, R.string.main_getWrongNumber, Toast.LENGTH_SHORT).show();
                    }else{
                        getPhoneNumber();
                        checkPermission();
                    }
                });

                thread.run();
            }
        );
    }

    private void hideKeypad(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextPhoneNumber.getWindowToken(),0);
    }

    private void getPhoneNumber() {
        String phoneNumber = editTextPhoneNumber.getText().toString();

        DebugLogUtil.logD(TAG, phoneNumber);

        SharedPreferenceUtil shUnit = new SharedPreferenceUtil();
        shUnit.RegisteredSharedPreference(this, Constant.sharedPreference_phoneNumber, phoneNumber);


    }

    private void checkPermission(){

        int permissionResult = MainActivity.this.checkSelfPermission(Manifest.permission.READ_SMS);
        if(permissionResult == PackageManager.PERMISSION_GRANTED) {
            ActivityUtil.startActivityNoFinish(MainActivity.this, SelectSmsActivity.class);
        }else{
            Toast.makeText(this, "권한을 확인해줘야 합니다 ㅠㅜ", Toast.LENGTH_SHORT).show();
            CheckPermissionUtil.checkPermission(MainActivity.this, Constant.manifest_permission_Read_SMS);
        }
    }
}