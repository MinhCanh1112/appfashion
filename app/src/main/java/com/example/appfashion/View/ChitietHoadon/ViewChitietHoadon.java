package com.example.appfashion.View.ChitietHoadon;

import com.example.appfashion.Model.Sanpham.ModelSanpham;

import java.util.List;

public interface ViewChitietHoadon {
    void ShowListChitietHoadonSuccess(List<ModelSanpham> sanphamList);
    void Loitruycap(Throwable t);
}
