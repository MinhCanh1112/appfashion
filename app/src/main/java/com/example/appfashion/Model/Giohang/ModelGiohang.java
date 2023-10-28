package com.example.appfashion.Model.Giohang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appfashion.Model.Sanpham.ModelSanpham;

import java.util.ArrayList;
import java.util.List;

public class ModelGiohang {
    SQLiteDatabase database;
    public void MoKetNoiSQL(Context context){
        DBGiohang dbGiohang = new DBGiohang(context);
        database = dbGiohang.getWritableDatabase();

    }

    public boolean XoaSanphamTrongGiohang(int id){
       int kiemtra = database.delete(DBGiohang.TB_GIOHANG,DBGiohang.TB_GIOHANG_ID+" = "+id,null);
       if(kiemtra > 0){
           return true;
       }else{
           return false;
       }
    }

    public boolean XoaSanphamTrongGiohangTheoId(int[] idArray){
        StringBuilder idValues = new StringBuilder();
        for (int i = 0; i < idArray.length; i++) {
            idValues.append(idArray[i]);
            if (i < idArray.length - 1) {
                idValues.append(",");
            }
        }
        int kiemtra = database.delete(DBGiohang.TB_GIOHANG,DBGiohang.TB_GIOHANG_ID+" IN (" + idValues.toString() + ")",null);
        if(kiemtra > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean UpdateSoluongSpGiohang(int id, int soluong){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBGiohang.TB_GIOHANG_SOLUONG,soluong);
        int kiemtra = database.update(DBGiohang.TB_GIOHANG,contentValues,DBGiohang.TB_GIOHANG_ID +" = "+id,null);
        if (kiemtra>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean ThemGiohang(ModelSanpham sanpham) {

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay không
        Cursor cursor = database.query(DBGiohang.TB_GIOHANG, null,
                DBGiohang.TB_GIOHANG_IDSP + " = ? AND " + DBGiohang.TB_GIOHANG_SIZE + " = ?",
                new String[]{String.valueOf(sanpham.getIdsp()), sanpham.getSize()},
                null, null, null);

        if (cursor.moveToFirst()) {
            // Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
            int existingQuantity = cursor.getInt(5);
            int newQuantity = existingQuantity + sanpham.getSoluong();

            ContentValues updateValues = new ContentValues();
            updateValues.put(DBGiohang.TB_GIOHANG_SOLUONG, newQuantity);

            int rowsAffected = database.update(DBGiohang.TB_GIOHANG, updateValues,
                    DBGiohang.TB_GIOHANG_IDSP + " = ? AND " + DBGiohang.TB_GIOHANG_SIZE + " = ?",
                    new String[]{String.valueOf(sanpham.getIdsp()), sanpham.getSize()});

            cursor.close();
            database.close();

            return rowsAffected > 0;
        } else {
            // Sản phẩm chưa tồn tại trong giỏ hàng, thêm mới sản phẩm
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBGiohang.TB_GIOHANG_IDSP, sanpham.getIdsp());
            contentValues.put(DBGiohang.TB_GIOHANG_TENSP, sanpham.getTensp());
            contentValues.put(DBGiohang.TB_GIOHANG_SIZE, sanpham.getSize());
            contentValues.put(DBGiohang.TB_GIOHANG_GIASP, sanpham.getGia());
            contentValues.put(DBGiohang.TB_GIOHANG_SOLUONG, sanpham.getSoluong());
            contentValues.put(DBGiohang.TB_GIOHANG_HINHANH, sanpham.getHinhgiohang());

            long id = database.insert(DBGiohang.TB_GIOHANG, null, contentValues);

            database.close();

            return id > 0;
        }
    }


    public List<ModelSanpham> getDataGiohang(){
        List<ModelSanpham> sanphamList = new ArrayList<>();
        String truyvan = "SELECT * FROM " + DBGiohang.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            int idsp = cursor.getInt(1);
            String tensp = cursor.getString(2);
            String size = cursor.getString(3);
            int giasp = cursor.getInt(4);
            int soluong = cursor.getInt(5);
            byte [] hinhanh = cursor.getBlob(6);
            ModelSanpham sanpham = new ModelSanpham();
            sanpham.setId(id);
            sanpham.setIdsp(idsp);
            sanpham.setTensp(tensp);
            sanpham.setSize(size);
            sanpham.setGia(giasp);
            sanpham.setSoluong(soluong);
            sanpham.setHinhgiohang(hinhanh);
            sanphamList.add(sanpham);
            cursor.moveToNext();
        }
        cursor.close();
        return sanphamList;
    }


    public List<ModelSanpham> getDataGiohangTheoId(int[] idArray) {
        List<ModelSanpham> sanphamList = new ArrayList<>();
        // Tạo danh sách các giá trị id dưới dạng chuỗi
        StringBuilder idValues = new StringBuilder();
        for (int i = 0; i < idArray.length; i++) {
            idValues.append(idArray[i]);
            if (i < idArray.length - 1) {
                idValues.append(",");
            }
        }
        // Tạo câu truy vấn sử dụng IN để lấy dữ liệu theo id
        String truyvan = "SELECT * FROM " + DBGiohang.TB_GIOHANG + " WHERE " + DBGiohang.TB_GIOHANG_ID + " IN (" + idValues.toString() + ")";
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            int idsp = cursor.getInt(1);
            String tensp = cursor.getString(2);
            String size = cursor.getString(3);
            int giasp = cursor.getInt(4);
            int soluong = cursor.getInt(5);
            byte [] hinhanh = cursor.getBlob(6);
            ModelSanpham sanpham = new ModelSanpham();
            sanpham.setId(id);
            sanpham.setIdsp(idsp);
            sanpham.setTensp(tensp);
            sanpham.setSize(size);
            sanpham.setGia(giasp);
            sanpham.setSoluong(soluong);
            sanpham.setHinhgiohang(hinhanh);
            sanphamList.add(sanpham);
            cursor.moveToNext();
        }

        cursor.close();
        return sanphamList;
    }


}
