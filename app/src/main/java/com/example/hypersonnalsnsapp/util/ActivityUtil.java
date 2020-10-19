package com.example.hypersonnalsnsapp.util;

import android.content.Context;
import android.content.Intent;

import com.example.hypersonnalsnsapp.main.MainActivity;

public class ActivityUtil {

    //private static final ActivityUtil instance = new ActivityUtil();

    public ActivityUtil() {
        new ActivityUtil();
    };

    public static void startActivityNoFinish(Context startContext, Class<?> activityClass){
        Intent intent= new Intent(startContext, activityClass);
        startContext.startActivity(intent);
    }

}
