package com.example.appfashion.Presenter.DetailProduct;

import android.content.Context;

import com.example.appfashion.Model.Sanpham.ModelSanpham;

public interface iPresenterDetailProduct {
    void getDataSp(int idsp);
    void ThemSanpham(ModelSanpham sanpham, Context context);
}
