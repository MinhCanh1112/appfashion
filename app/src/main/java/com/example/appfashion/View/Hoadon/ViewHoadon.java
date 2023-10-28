package com.example.appfashion.View.Hoadon;

import com.example.appfashion.Model.Hoadon.ModelHoadon;

import java.util.List;

public interface ViewHoadon {
    void ShowListHoadonSuccess(List<ModelHoadon> hoadonList);
    void Loitruycap(Throwable t);
}
