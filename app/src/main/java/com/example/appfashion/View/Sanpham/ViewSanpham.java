package com.example.appfashion.View.Sanpham;

import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Model.Sanpham.ModelSanpham;

import java.util.List;

public interface ViewSanpham {
    void ShowListSpSuccess(List<ModelSanpham> sanphamList);
    void ShowListLoaiSpSuccess(List<ModelLoaiSanPham> loaiSanPhams);
    void Loitruycap(Throwable t);
}
