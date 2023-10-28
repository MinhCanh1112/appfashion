package com.example.appfashion.View.Thanhtoan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfashion.Adapter.ThanhtoanAdapter;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Presenter.Thanhtoan.PresenterThanhtoan;
import com.example.appfashion.R;
import com.example.appfashion.View.Home.HomeActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThanhtoanActivity extends AppCompatActivity implements ViewThanhtoan{
    Toolbar toolbar;
    ThanhtoanAdapter thanhtoanAdapter;
    RecyclerView recyc_sp;
    PresenterThanhtoan presenterThanhtoan;
    AppCompatButton thanhtoan;
    ArrayList<Integer> idspList = new ArrayList<>();
    ArrayList<Integer> soluongspList = new ArrayList<>();
    ArrayList<String> sizeList = new ArrayList<>();

    EditText edtname,edtsdt,edtdiachi;

    LinearLayout lineargone;
    ImageView imghinhsp;
    TextView txttensp,txtgiasp,txtsoluong,txtsize,txttongtien;

    int[] idArray;
    String idList,soluongsp,size;
    int tongtien =0;
    DecimalFormat decimalFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        Anhxa();
        ActionToolBar();
        setStatusBarColor();

        Intent intent = getIntent();
        idArray =intent.getIntArrayExtra("mangid");

        presenterThanhtoan = new PresenterThanhtoan(this);
        if(idArray != null){
            lineargone.setVisibility(View.GONE);
            recyc_sp.setVisibility(View.VISIBLE);
            presenterThanhtoan.getDataGiohangThanhtoan(this,idArray);
            size = sizeList.toString();
            idList = idspList.toString();
            soluongsp = soluongspList.toString();
            Log.e("idlistv",idList);
            Log.e("sizev",size);
            Log.e("sizeList", String.valueOf(sizeList));
            Log.e("soluongv",soluongsp);

            EventThanhtoan(idList,sizeList,soluongsp);
        }else {
            lineargone.setVisibility(View.VISIBLE);
            recyc_sp.setVisibility(View.GONE);
            int idsp  = intent.getIntExtra("idsp",-1);
            int giasp  = intent.getIntExtra("giasp",-1);
            int soluong  = intent.getIntExtra("soluong",-1);
            idspList.add(idsp);
            soluongspList.add(soluong);
            idList = idspList.toString();
            soluongsp = soluongspList.toString();

            String tensp = intent.getStringExtra("tensp");
            String sizes = intent.getStringExtra("size");
            sizeList.add(sizes);

            String hinhsp = intent.getStringExtra("hinhsp");
            String gia = decimalFormat.format(giasp)+"đ";

            if(sizes.equals("-1")||sizes ==null){
                txtsize.setVisibility(View.GONE);
            }
            txtsize.setText("Size: "+sizes+", ");
            txtgiasp.setText(gia);
            txttensp.setText(tensp);
            txtsoluong.setText(String.valueOf(soluong));
            Glide.with(this).load(hinhsp).into(imghinhsp);
            tongtien = giasp*soluong;
            txttongtien.setText("Tổng thanh toán: "+decimalFormat.format(tongtien)+"đ");

            EventThanhtoan(idList,sizeList,soluongsp);
        }
    }

    private void EventThanhtoan(String idsp,  ArrayList<String> sizeList,String soluong) {
        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtname.getText().toString().trim();
                String mobile = edtsdt.getText().toString().trim();
                String diachi = edtdiachi.getText().toString().trim();

                if(name.isEmpty()||mobile.isEmpty()||diachi.isEmpty()){
                    Toast.makeText(ThanhtoanActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else if(mobile.length()!=10){
                    Toast.makeText(ThanhtoanActivity.this, "Số điện thoại không chính xác", Toast.LENGTH_SHORT).show();
                }else{
                    presenterThanhtoan.Thanhtoan(name,mobile,diachi,tongtien,idArray,idsp,sizeList,soluong,ThanhtoanActivity.this);
                }
            }
        });
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        txttongtien = findViewById(R.id.tvdathang_sum);
        toolbar = findViewById(R.id.toolbar_thanhtoan);
        recyc_sp = findViewById(R.id.recyc_sp);
        lineargone= findViewById(R.id.lineargone);
        imghinhsp = findViewById(R.id.img1_sp_order);
        txttensp = findViewById(R.id.tv1_sp_name);
        txtgiasp = findViewById(R.id.tv1_sp_gia);
        txtsoluong = findViewById(R.id.tv_sp_sl);
        txtsize = findViewById(R.id.tv1_sp_size);
        thanhtoan = findViewById(R.id.dathang);
        edtname = findViewById(R.id.namedc);
        edtsdt = findViewById(R.id.mobiledc);
        edtdiachi = findViewById(R.id.edt_diachi);
        decimalFormat = new DecimalFormat("###,###,###");
    }

    @Override
    public void ShowListSpThanhtoanSucces(List<ModelSanpham> sanphamList) {
        for (int i = 0; i < sanphamList.size(); i++) {
            ModelSanpham sanpham = sanphamList.get(i);
            int idsp = sanpham.getIdsp();
            int soluongsp = sanpham.getSoluong();
            String sizes = sanpham.getSize();
            idspList.add(idsp);
            soluongspList.add(soluongsp);
            sizeList.add(sizes);
            tongtien += sanpham.getGia()*sanpham.getSoluong();
        }

        txttongtien.setText("Tổng thanh toán: "+decimalFormat.format(tongtien)+"đ");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyc_sp.setLayoutManager(linearLayoutManager);
        thanhtoanAdapter = new ThanhtoanAdapter(ThanhtoanActivity.this,sanphamList);
        recyc_sp.setAdapter(thanhtoanAdapter);
        thanhtoanAdapter.notifyDataSetChanged();
    }

    @Override
    public void ThanhtoanThanhcong() {
        Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ThanhtoanActivity.this, HomeActivity.class);
        startActivity(intent);
        finishAffinity();

    }

    @Override
    public void ThanhtoanThatbai(ModelSanpham sanpham) {
        Toast.makeText(this, sanpham.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoitruycapThanhtoan(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}