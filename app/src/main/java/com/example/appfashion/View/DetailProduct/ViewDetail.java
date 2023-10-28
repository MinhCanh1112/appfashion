package com.example.appfashion.View.DetailProduct;

import com.example.appfashion.Model.Sanpham.ModelSanpham;

public interface ViewDetail {
    void ShowDataSanpham(ModelSanpham sanpham);
    void loitruycap(Throwable t);

    void ThemGiohangSuccess();
    void ThemGiohangFail();
}
