package com.example.hypersonnalsnsapp.util;

import android.util.Log;

import java.util.List;

public class DebugLogUtil {

    private static boolean isDebugged=true;

    public static final void logD(String TAG, Object obj){
        if(DebugLogUtil.isDebugged){
            Log.d(TAG, buildMsg(obj));
        }
    }


    private static String buildMsg(Object obj){

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();

        sb.append("DebugLogUnit : ");
        sb.append(ste.getFileName().replace(".java",""));
        sb.append(" :: ");
        sb.append(obj);

        return sb.toString();
    }


}
