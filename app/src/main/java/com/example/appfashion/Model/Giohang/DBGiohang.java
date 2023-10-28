package com.example.appfashion.Model.Giohang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBGiohang extends SQLiteOpenHelper {
    public static String TB_GIOHANG = "GIOHANG";
    public static String TB_GIOHANG_ID = "ID";
    public static String TB_GIOHANG_IDSP = "IDSP";
    public static String TB_GIOHANG_TENSP = "TENSP";
    public static String TB_GIOHANG_SIZE = "SIZE";
    public static String TB_GIOHANG_GIASP = "GIASP";
    public static String TB_GIOHANG_SOLUONG = "SOLUONG";
    public static String TB_GIOHANG_HINHANH = "HINHANH";
    public DBGiohang(Context context) {
            super(context, "SQLSANPHAM", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbGioHang = "CREATE TABLE "+TB_GIOHANG+"("+
                TB_GIOHANG_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TB_GIOHANG_IDSP+" INTEGER, "+
                TB_GIOHANG_TENSP+" TEXT, "+
                TB_GIOHANG_SIZE+" TEXT, "+
                TB_GIOHANG_GIASP+" REAL, "+
                TB_GIOHANG_SOLUONG+" INTEGER, "+
                TB_GIOHANG_HINHANH+" BLOB)";

        db.execSQL(tbGioHang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
