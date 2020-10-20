package com.example.hypersonnalsnsapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private static final String TAG = "SharedPreferenceUnit";

    public static String SHARED_PREFERENCES_KEY="SharedPreferences_Key";

    public void RegisteredSharedPreference(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key, value);
        DebugLogUtil.logD(TAG, "key : "+key+", value : "+ value);
        editor.commit();

    }

}
