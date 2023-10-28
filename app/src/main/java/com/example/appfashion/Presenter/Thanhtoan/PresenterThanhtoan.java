package com.example.appfashion.Presenter.Thanhtoan;

import android.content.Context;

import com.example.appfashion.Model.Giohang.ModelGiohang;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Thanhtoan.ViewThanhtoan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterThanhtoan implements iPresenterThanhtoan{
    ViewThanhtoan viewThanhtoan;
    ModelGiohang modelGiohang;
    List<ModelSanpham> sanphamList;
    public PresenterThanhtoan(ViewThanhtoan viewThanhtoan) {
        this.viewThanhtoan = viewThanhtoan;
        modelGiohang = new ModelGiohang();
    }

    @Override
    public void getDataGiohangThanhtoan(Context context,int[] id) {
        modelGiohang.MoKetNoiSQL(context);
        sanphamList = modelGiohang.getDataGiohangTheoId(id);
        if(sanphamList.size()>0){
            viewThanhtoan.ShowListSpThanhtoanSucces(sanphamList);
        }
    }

    @Override
    public void Thanhtoan(String name, String mobile, String diachi,int tongtien, int[] id, String idsp, ArrayList<String> sizeList, String soluong, Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = sdf.format(new Date());
        int iduser = Utils.getUserId(context);
        String size = sizeList.toString();
        Call<ModelSanpham> call = Utils.apiSanpham.Thanhtoan(iduser,name,mobile,diachi,datetime,tongtien,idsp,size,soluong);
        call.enqueue(new Callback<ModelSanpham>() {
            @Override
            public void onResponse(Call<ModelSanpham> call, Response<ModelSanpham> response) {
                ModelSanpham sanpham = response.body();
                if(sanpham != null && sanpham.isSuccess()){
                    viewThanhtoan.ThanhtoanThanhcong();
                    modelGiohang.MoKetNoiSQL(context);
                    if (id != null) {
                        modelGiohang.XoaSanphamTrongGiohangTheoId(id);
                    }


                }else {
                    viewThanhtoan.ThanhtoanThatbai(sanpham);
                }

            }

            @Override
            public void onFailure(Call<ModelSanpham> call, Throwable t) {
                viewThanhtoan.LoitruycapThanhtoan(t);
            }
        });
    }
}
