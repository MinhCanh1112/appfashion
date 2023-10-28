package com.example.appfashion.View.Home;

import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Model.Home.ModelQuangcao;
import com.example.appfashion.Model.Sanpham.ModelSanpham;

import java.util.List;

public interface ViewHome {
    void ShowListSpSuccess(List<ModelSanpham> sanphamList);
    void ShowListSpFail(Throwable t);

    void ShowListLoaispSuccess(List<ModelLoaiSanPham> loaiSanPhamList);
    void ShowListLoaispFail(Throwable t);

    void ShowListBannerSuccess(List<ModelQuangcao> quangcaoList);
    void ShowListBannerFail(Throwable t);
}
