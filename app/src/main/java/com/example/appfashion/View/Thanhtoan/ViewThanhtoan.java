package com.example.appfashion.View.Thanhtoan;

import com.example.appfashion.Model.Sanpham.ModelSanpham;

import java.util.List;

public interface ViewThanhtoan {
    void ShowListSpThanhtoanSucces(List<ModelSanpham> sanphamList);
    void ThanhtoanThanhcong();
    void ThanhtoanThatbai(ModelSanpham sanpham);
    void LoitruycapThanhtoan(Throwable t);
}
