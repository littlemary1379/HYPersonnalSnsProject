package com.example.hypersonnalsnsapp.util;

import android.Manifest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {

    //KEY
    public static String sharedPreference_phoneNumber = "sharedPreference_phoneNumber";

    //퍼미션 부여
    public static String manifest_permission_Read_SMS = Manifest.permission.READ_SMS;
    public static String manifest_permission_Read_Contact = Manifest.permission.READ_CONTACTS;

    //제품 목록
    public static List<String> productList = new ArrayList<>(Arrays.asList("제품 1", "제품 2", "제품 3", "제품 4", "제품 5"));
    public static List<String> productOneCostList = new ArrayList<>(Arrays.asList("200원", "2000원", "1500원", "700원", "2500원"));
}
