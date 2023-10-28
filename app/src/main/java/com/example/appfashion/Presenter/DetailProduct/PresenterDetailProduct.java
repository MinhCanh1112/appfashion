package com.example.appfashion.Presenter.DetailProduct;

import android.content.Context;

import com.example.appfashion.Model.Giohang.ModelGiohang;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.DetailProduct.ViewDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterDetailProduct implements iPresenterDetailProduct{
    ViewDetail viewDetail;
    ModelGiohang giohang;

    public PresenterDetailProduct() {
        giohang = new ModelGiohang();
    }

    public PresenterDetailProduct(ViewDetail viewDetail) {
        this.viewDetail = viewDetail;
        giohang = new ModelGiohang();
    }

    @Override
    public void getDataSp(int idsp) {
        Call<ModelSanpham> call = Utils.apiSanpham.getDataSanpham(idsp);
        call.enqueue(new Callback<ModelSanpham>() {
            @Override
            public void onResponse(Call<ModelSanpham> call, Response<ModelSanpham> response) {
                ModelSanpham sanpham = response.body();
                viewDetail.ShowDataSanpham(sanpham);

            }

            @Override
            public void onFailure(Call<ModelSanpham> call, Throwable t) {
                viewDetail.loitruycap(t);
            }
        });
    }

    @Override
    public void ThemSanpham(ModelSanpham sanpham, Context context) {
        giohang.MoKetNoiSQL(context);
        boolean kiemtra = giohang.ThemGiohang(sanpham);
        if(kiemtra){
            viewDetail.ThemGiohangSuccess();
        }else{
            viewDetail.ThemGiohangFail();
        }
    }

    public int DemSoLuongSanphamTrongGiohang(Context context){
        giohang.MoKetNoiSQL(context);
        return giohang.getDataGiohang().size();
    }
}
