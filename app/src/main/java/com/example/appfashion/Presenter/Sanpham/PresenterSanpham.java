package com.example.appfashion.Presenter.Sanpham;

import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Sanpham.ViewSanpham;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterSanpham implements iPresenterSanpham{
    ViewSanpham viewSanpham;
    private List<ModelSanpham> sanphamList;
    private List<ModelLoaiSanPham> loaiSanPhamList;
    private List<ModelSanpham> phanloaiList;
    public PresenterSanpham(ViewSanpham viewSanpham) {
        this.viewSanpham = viewSanpham;
    }

    @Override
    public void getSp(int idloaisp) {
        Call<List<ModelSanpham>> call = Utils.apiSanpham.getSp(idloaisp);
        call.enqueue(new Callback<List<ModelSanpham>>() {
            @Override
            public void onResponse(Call<List<ModelSanpham>> call, Response<List<ModelSanpham>> response) {
                sanphamList = response.body();
                viewSanpham.ShowListSpSuccess(sanphamList);
            }

            @Override
            public void onFailure(Call<List<ModelSanpham>> call, Throwable t) {
                viewSanpham.Loitruycap(t);
            }
        });
    }

    @Override
    public void getLoaisp() {
        Call<List<ModelLoaiSanPham>> call = Utils.apiSanpham.getLoaisp();
        call.enqueue(new Callback<List<ModelLoaiSanPham>>() {
            @Override
            public void onResponse(Call<List<ModelLoaiSanPham>> call, Response<List<ModelLoaiSanPham>> response) {
                loaiSanPhamList = response.body();
                viewSanpham.ShowListLoaiSpSuccess(loaiSanPhamList);

            }
            @Override
            public void onFailure(Call<List<ModelLoaiSanPham>> call, Throwable t) {
                viewSanpham.Loitruycap(t);
            }
        });
    }

    @Override
    public void getDataPhanloai(String phanloai) {
        Call<List<ModelSanpham>> call = Utils.apiSanpham.getDataPhanloai(phanloai);
        call.enqueue(new Callback<List<ModelSanpham>>() {
            @Override
            public void onResponse(Call<List<ModelSanpham>> call, Response<List<ModelSanpham>> response) {
                phanloaiList = response.body();
                viewSanpham.ShowListSpSuccess(phanloaiList);
            }

            @Override
            public void onFailure(Call<List<ModelSanpham>> call, Throwable t) {
                viewSanpham.Loitruycap(t);
            }
        });
    }
}
