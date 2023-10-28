package com.example.appfashion.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.appfashion.Retrofit.ApiSanpham;
import com.example.appfashion.Retrofit.ApiUser;
import com.example.appfashion.Retrofit.RetrofitClient;

public class Utils {
    public static final String BaseUrl = "https://fashion-1112.000webhostapp.com/fashion/";
//    public static final String BaseUrl = "http://192.168.1.7/fashion/";
    public static ApiSanpham apiSanpham = RetrofitClient.getInstance(BaseUrl).create(ApiSanpham.class);
    public static ApiUser apiUser = RetrofitClient.getInstance(BaseUrl).create(ApiUser.class);


    public static void saveUser(Context context, int iduser) {
        //tao doi tuong sharepreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        //goi phuong thuc editor de chinh sua
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //them du lieu
        editor.putInt("iduser", iduser);
        editor.apply();
    }

    public static int getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("iduser", -1);
    }

    public static void deleteSharePrefer(Context context){
        //tao doi tuong sharepreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        //goi phuong thuc editor de chinh sua
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //xoa du lieu
        editor.clear();
        editor.apply();
    }


}
