package com.example.hypersonnalsnsapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
        CheckPermissionUtil.checkPermission(MainActivity.this, Constant.manifest_permission_Read_Contact);
        checkSharedReference();

    }

    private void checkSharedReference() {
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(SharedPreferenceUtil.SHARED_PREFERENCES_KEY, Activity.MODE_PRIVATE);
        String storedPhoneNumber = sharedPreferences.getString(Constant.sharedPreference_phoneNumber, "");

        if (!storedPhoneNumber.equals("")) {
            ActivityUtil.startActivityFinish(MainActivity.this, SelectSmsActivity.class);
        }
    }

    private void findView() {
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        textViewNextButton = findViewById(R.id.textViewNextButton);
    }

    private String getMyPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            MainActivity.this.requestPermissions(new String[]{Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE},1001);
        }
        String phoneNum = telephonyManager.getLine1Number();
        if(phoneNum.startsWith("+82")){
            phoneNum=phoneNum.replace("+82","0");
        }

        return phoneNum;
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

                        String phoneNumber = editTextPhoneNumber.getText().toString();

                        if (phoneNumber.contains("-")) {
                            phoneNumber.replace("-", "");
                        }

                        if (phoneNumber.contains("/")) {
                            phoneNumber.replace("/", "");
                        }

                        if (phoneNumber.contains(" ")) {
                            phoneNumber.replace(" ", "");
                            phoneNumber.trim();
                        }

                        if (phoneNumber.length() < 11) {
                            DebugLogUtil.logD(TAG, "비어있음");
                            Toast.makeText(this, R.string.main_getWrongNumber, Toast.LENGTH_SHORT).show();
                            return;

                        } else if (!phoneNumber.equals(getMyPhoneNumber())) {
                            DebugLogUtil.logD(TAG, getMyPhoneNumber());
                            Toast.makeText(this, R.string.main_getOtherNumber, Toast.LENGTH_SHORT).show();
                            return;

                        } else {
                            getPhoneNumber();
                            checkPermission();
                        }
                    });

                    thread.run();
                }
        );
    }

    private void hideKeypad() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextPhoneNumber.getWindowToken(), 0);
    }

    private void getPhoneNumber() {
        String phoneNumber = editTextPhoneNumber.getText().toString();

        DebugLogUtil.logD(TAG, phoneNumber);

        SharedPreferenceUtil shUnit = new SharedPreferenceUtil();
        shUnit.RegisteredSharedPreference(this, Constant.sharedPreference_phoneNumber, phoneNumber);


    }

    private void checkPermission() {

        int permissionResultReadSMS = MainActivity.this.checkSelfPermission(Constant.manifest_permission_Read_SMS);
        int permissionResultReadContact = MainActivity.this.checkSelfPermission(Constant.manifest_permission_Read_Contact);
        if (permissionResultReadSMS == PackageManager.PERMISSION_GRANTED && permissionResultReadContact == PackageManager.PERMISSION_GRANTED) {
            ActivityUtil.startActivityNoFinish(MainActivity.this, SelectSmsActivity.class);
        } else {
            if (permissionResultReadSMS == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "권한을 확인해줘야 합니다 ㅠㅜ", Toast.LENGTH_SHORT).show();
                CheckPermissionUtil.checkPermission(MainActivity.this, Constant.manifest_permission_Read_SMS);
            } else if (permissionResultReadContact == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "권한을 확인해줘야 합니다 ㅠㅜ", Toast.LENGTH_SHORT).show();
                CheckPermissionUtil.checkPermission(MainActivity.this, Constant.manifest_permission_Read_Contact);
            }
        }
    }
}