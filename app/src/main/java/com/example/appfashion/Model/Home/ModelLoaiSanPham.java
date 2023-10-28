package com.example.appfashion.Model.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelLoaiSanPham implements Serializable {

    @SerializedName("idloaisp")
    @Expose
    private String idloaisp;
    @SerializedName("tenloaisp")
    @Expose
    private String tenloaisp;
    @SerializedName("hinhanhloaisp")
    @Expose
    private String hinhanhloaisp;

    public String getIdloaisp() {
    return idloaisp;
    }

    public void setIdloaisp(String idloaisp) {
    this.idloaisp = idloaisp;
    }

    public String getTenloaisp() {
    return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
    this.tenloaisp = tenloaisp;
    }

    public String getHinhanhloaisp() {
    return hinhanhloaisp;
    }

    public void setHinhanhloaisp(String hinhanhloaisp) {
    this.hinhanhloaisp = hinhanhloaisp;
    }

}