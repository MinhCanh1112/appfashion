package com.example.appfashion.Presenter.Thanhtoan;

import android.content.Context;

import java.util.ArrayList;

public interface iPresenterThanhtoan {
    void getDataGiohangThanhtoan(Context context,int[] id);
    void Thanhtoan(String name, String mobile, String diachi,int tongtien, int[] id, String idsp, ArrayList<String> sizeList, String soluong, Context context);
}
