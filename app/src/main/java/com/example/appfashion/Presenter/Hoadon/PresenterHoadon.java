package com.example.appfashion.Presenter.Hoadon;


import android.content.Context;

import com.example.appfashion.Model.Hoadon.ModelHoadon;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Hoadon.ViewHoadon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterHoadon implements iPresenterHoadon{
    ViewHoadon viewHoadon;
    List<ModelHoadon> hoadonList;
    public PresenterHoadon(ViewHoadon viewHoadon) {
        this.viewHoadon = viewHoadon;
    }

    @Override
    public void getDataHoadon(Context context) {
        int iduser = Utils.getUserId(context);
        Call<List<ModelHoadon>> call = Utils.apiSanpham.getDataHoadon(iduser);
        call.enqueue(new Callback<List<ModelHoadon>>() {
            @Override
            public void onResponse(Call<List<ModelHoadon>> call, Response<List<ModelHoadon>> response) {
                hoadonList = response.body();
                viewHoadon.ShowListHoadonSuccess(hoadonList);
            }

            @Override
            public void onFailure(Call<List<ModelHoadon>> call, Throwable t) {
                viewHoadon.Loitruycap(t);
            }
        });
    }
}
