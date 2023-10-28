package com.example.appfashion.Presenter.Home;

import androidx.annotation.NonNull;

import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Model.Home.ModelQuangcao;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Home.ViewHome;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterHome implements iPresenterHome{
    ViewHome viewHome;
    private List<ModelSanpham> sanphamList;
    private List<ModelLoaiSanPham> loaiSanPhamList;
    private List<ModelQuangcao> quangcaoList;

    public PresenterHome(){}

    public PresenterHome(ViewHome viewHome) {
        this.viewHome = viewHome;
    }

    @Override
    public void getDataSp() {
        Call<List<ModelSanpham>> call = Utils.apiSanpham.getAllsp();
        call.enqueue(new Callback<List<ModelSanpham>>() {
            @Override
            public void onResponse(@NonNull Call<List<ModelSanpham>> call, @NonNull Response<List<ModelSanpham>> response) {
                sanphamList = response.body();
                viewHome.ShowListSpSuccess(sanphamList);
            }

            @Override
            public void onFailure(@NonNull Call<List<ModelSanpham>> call, @NonNull Throwable t) {
                viewHome.ShowListSpFail(t);

            }
        });
    }

    @Override
    public void getDataLoaiSp() {
        Call<List<ModelLoaiSanPham>> call = Utils.apiSanpham.getLoaisp();
        call.enqueue(new Callback<List<ModelLoaiSanPham>>() {
            @Override
            public void onResponse(Call<List<ModelLoaiSanPham>> call, Response<List<ModelLoaiSanPham>> response) {
                loaiSanPhamList = response.body();
                viewHome.ShowListLoaispSuccess(loaiSanPhamList);

            }
            @Override
            public void onFailure(Call<List<ModelLoaiSanPham>> call, Throwable t) {
                viewHome.ShowListLoaispFail(t);
            }
        });
    }

    @Override
    public void getDataBanner() {
        Call<List<ModelQuangcao>> call = Utils.apiSanpham.getBanner();
        call.enqueue(new Callback<List<ModelQuangcao>>() {
            @Override
            public void onResponse(Call<List<ModelQuangcao>> call, Response<List<ModelQuangcao>> response) {
                quangcaoList = (List<ModelQuangcao>) response.body();
                viewHome.ShowListBannerSuccess(quangcaoList);
            }
            @Override
            public void onFailure(Call<List<ModelQuangcao>> call, Throwable t) {
                viewHome.ShowListBannerFail(t);

            }
        });
    }
}
