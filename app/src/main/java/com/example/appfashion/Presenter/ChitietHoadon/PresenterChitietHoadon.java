package com.example.appfashion.Presenter.ChitietHoadon;

import android.content.Context;

import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.ChitietHoadon.ViewChitietHoadon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterChitietHoadon implements iPresenterChitietHoadon{
    ViewChitietHoadon viewChitietHoadon;
    List<ModelSanpham> sanphamList;
    public PresenterChitietHoadon(ViewChitietHoadon viewChitietHoadon) {
        this.viewChitietHoadon = viewChitietHoadon;
    }

    @Override
    public void getDataChitietHoadon(Context context,int idhoadon) {
        int iduser = Utils.getUserId(context);
        Call<List<ModelSanpham>> call = Utils.apiSanpham.getDataChitietHoadon(iduser,idhoadon);
        call.enqueue(new Callback<List<ModelSanpham>>() {
            @Override
            public void onResponse(Call<List<ModelSanpham>> call, Response<List<ModelSanpham>> response) {
                sanphamList = response.body();
                viewChitietHoadon.ShowListChitietHoadonSuccess(sanphamList);
            }

            @Override
            public void onFailure(Call<List<ModelSanpham>> call, Throwable t) {
                viewChitietHoadon.Loitruycap(t);
            }
        });
    }
}
