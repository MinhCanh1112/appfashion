package com.example.appfashion.Retrofit;


import com.example.appfashion.Model.Hoadon.ModelHoadon;
import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Model.Home.ModelQuangcao;
import com.example.appfashion.Model.Sanpham.ModelSanpham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiSanpham {

    @POST("getSp.php")
    @FormUrlEncoded
    Call<List<ModelSanpham>> getSp(
            @Field("idloaisp") int idloaisp
    );

    @POST("getDataHoadon.php")
    @FormUrlEncoded
    Call<List<ModelHoadon>> getDataHoadon(
            @Field("iduser") int iduser
    );

    @POST("getDataChitietHoadon.php")
    @FormUrlEncoded
    Call<List<ModelSanpham>> getDataChitietHoadon(
            @Field("iduser") int iduser,
            @Field("idhoadon") int idhoadon
    );

    @POST("getDataSp.php")
    @FormUrlEncoded
    Call<ModelSanpham> getDataSanpham(
            @Field("idsp") int idsp );

    @POST("getDataPhanLoai.php")
    @FormUrlEncoded
    Call<List<ModelSanpham>> getDataPhanloai(
            @Field("phanloai") String phanloai );

    @POST("Thanhtoan.php")
    @FormUrlEncoded
    Call<ModelSanpham> Thanhtoan(
            @Field("iduser") int iduser,
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("diachi") String diachi,
            @Field("datetime") String datetime,
            @Field("tongtien") int tongtien,
            @Field("idsp") String idsp,
            @Field("size") String size,
            @Field("soluong") String soluong
    );

    @GET("getLoaisp.php")
    Call<List<ModelLoaiSanPham>> getLoaisp();

    @GET("getBanner.php")
    Call<List<ModelQuangcao>> getBanner();

    @GET("getAllSp.php")
    Call<List<ModelSanpham>> getAllsp();





}