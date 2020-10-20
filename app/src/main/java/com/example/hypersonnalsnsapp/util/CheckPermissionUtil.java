package com.example.hypersonnalsnsapp.util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CheckPermissionUtil {

    private static final String TAG = "CheckPermissionUtil";
    private static final int MY_PERMISSION_REQUEST_READ_SMS = 1001;
    private static boolean result;

    public CheckPermissionUtil() {
        new CheckPermissionUtil();
    }

    public static boolean checkPermission(Activity activity, String requestPermission) {

        String authority = "";

        if(requestPermission.equals(Constant.manifest_permission_Read_SMS)) {
            authority = "문자 목록을";
        }else if(requestPermission.equals(Constant.manifest_permission_Read_Contact)){
            authority = "연락처 목록을";
        }

        result = false;

        int permissionCheck = ContextCompat.checkSelfPermission(activity, requestPermission);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(activity, "권한 승인이 필요합니다.", Toast.LENGTH_SHORT).show();

            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setTitle("권한 승인 확인")
                        .setMessage(authority+" 확인하기 위한 권한이 필요합니다. 계속하시겠습니까?")
                        .setPositiveButton("네", (dialog1, which) -> {
                            result = true;
                            activity.requestPermissions(new String[]{requestPermission}, 1001);
                        })
                        .setNegativeButton("아니오", (dialog12, which) ->
                        {
                            Toast.makeText(activity, "권한 승인을 부여받지 못했습니다", Toast.LENGTH_SHORT).show();
                            result = false;
                        }).create().show();
            } else {
                DebugLogUtil.logD(TAG, "아니 잠깐 여기로 오니 너네?");
                activity.requestPermissions(new String[]{requestPermission}, 1001);
            }

        } else {
            result = true;
        }

        DebugLogUtil.logD(TAG, result);
        return result;
    }
}
