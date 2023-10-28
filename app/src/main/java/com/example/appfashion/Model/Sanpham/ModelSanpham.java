package com.example.appfashion.Model.Sanpham;

import java.io.Serializable;

public class ModelSanpham implements Serializable {
    private int id,idsp,idloaisp;
    private String tenloaisp,phanloai,tensp,size,mota;
    private int gia;
    private int soluong;
    private String hinhanh1;
    private String hinhanh2;
    private String hinhanh5,hinhanh6,hinhanh7;
    private String hinhanhsize;
    byte[] hinhgiohang;
    private boolean success;
    private String message;
    public byte[] getHinhgiohang() {
        return hinhgiohang;
    }

    public void setHinhgiohang(byte[] hinhgiohang) {
        this.hinhgiohang = hinhgiohang;
    }

    public ModelSanpham() {
    }

    public ModelSanpham(String hinhanh1) {
        super();
        this.hinhanh1 = hinhanh1;
    }

    public String getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(String phanloai) {
        this.phanloai = phanloai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getIdloaisp() {
        return idloaisp;
    }

    public void setIdloaisp(int idloaisp) {
        this.idloaisp = idloaisp;
    }

    public String getHinhanh6() {
        return hinhanh6;
    }

    public void setHinhanh6(String hinhanh6) {
        this.hinhanh6 = hinhanh6;
    }

    public String getHinhanh7() {
        return hinhanh7;
    }

    public void setHinhanh7(String hinhanh7) {
        this.hinhanh7 = hinhanh7;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public String getHinhanh5() {
        return hinhanh5;
    }

    public void setHinhanh5(String hinhanh5) {
        this.hinhanh5 = hinhanh5;
    }

    public String getHinhanhsize() {
        return hinhanhsize;
    }

    public void setHinhanhsize(String hinhanhsize) {
        this.hinhanhsize = hinhanhsize;
    }



    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhanh1() {
        return hinhanh1;
    }

    public void setHinhanh1(String hinhanh1) {
        this.hinhanh1 = hinhanh1;
    }

    public String getHinhanh2() {
        return hinhanh2;
    }

    public void setHinhanh2(String hinhanh2) {
        this.hinhanh2 = hinhanh2;
    }

}

