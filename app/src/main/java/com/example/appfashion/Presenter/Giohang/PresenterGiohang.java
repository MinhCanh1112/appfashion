package com.example.appfashion.Presenter.Giohang;

import android.content.Context;

import com.example.appfashion.Model.Giohang.ModelGiohang;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.View.Giohang.ViewGiohang;

import java.util.List;

public class PresenterGiohang implements iPresenterGiohang {
    ViewGiohang viewGiohang;
    ModelGiohang modelGiohang;
    List<ModelSanpham> sanphamList;

    public PresenterGiohang(){
        modelGiohang = new ModelGiohang();
    }

    public PresenterGiohang(ViewGiohang viewGiohang) {
        this.viewGiohang = viewGiohang;
        modelGiohang = new ModelGiohang();
    }

    @Override
    public void getDataGiohang(Context context) {
        modelGiohang.MoKetNoiSQL(context);
        sanphamList = modelGiohang.getDataGiohang();
        viewGiohang.ShowListGiohangSuccess(sanphamList);
//        if(sanphamList.size() > 0){
//            viewGiohang.ShowListGiohangSuccess(sanphamList);
//        }
    }
}
