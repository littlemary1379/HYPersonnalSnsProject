package com.example.hypersonnalsnsapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.hypersonnalsnsapp.R;
import com.example.hypersonnalsnsapp.main.MainActivity;

public class ActivityUtil {

    //private static final ActivityUtil instance = new ActivityUtil();

    public ActivityUtil() {
        new ActivityUtil();
    };

    public static void startActivityNoFinish(Activity startActivity, Class<?> activityClass){
        Intent intent= new Intent(startActivity, activityClass);
        startActivity.startActivity(intent);
        startActivity.overridePendingTransition(R.anim.in_left, R.anim.out_right);
    }

    public static void startActivityFinish(Activity startActivity, Class<?> activityClass){
        Intent intent= new Intent(startActivity, activityClass);
        startActivity.startActivity(intent);
        startActivity.overridePendingTransition(R.anim.in_left, R.anim.out_right);
        startActivity.finish();
    }

}
