package com.example.appfashion.View.Giohang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfashion.Adapter.GiohangAdapter;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Presenter.Giohang.PresenterGiohang;
import com.example.appfashion.R;
import com.example.appfashion.View.Thanhtoan.ThanhtoanActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GiohangActivity extends AppCompatActivity implements ViewGiohang{
    Toolbar toolbar;
    RecyclerView recyc_giohang;
    ImageView giohangtrong;

    GiohangAdapter giohangAdapter;
    PresenterGiohang presenterGiohang;
    AppCompatButton muahang;
    int[] idArray;

    List<ModelSanpham> modelSanphamList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolBar();
        setStatusBarColor();
        presenterGiohang.getDataGiohang(this);
        EventIntentThanhtoan();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenterGiohang.getDataGiohang(this);
    }

    private void EventIntentThanhtoan() {
        muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiohangActivity.this, ThanhtoanActivity.class);
                intent.putExtra("mangid",idArray);
                startActivity(intent);

            }
        });
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
        giohangtrong = findViewById(R.id.giohangtrong);
        muahang = findViewById(R.id.muahang1);
        toolbar = findViewById(R.id.toolbar_cart);
        recyc_giohang = findViewById(R.id.recyc_giohang);
        presenterGiohang = new PresenterGiohang(this);
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void ShowListGiohangSuccess(List<ModelSanpham> sanphamList) {
        idArray = new int[sanphamList.size()]; // Tạo mảng int[] để chứa các ID
        modelSanphamList = sanphamList;
        for (int i = 0; i < sanphamList.size(); i++) {
            ModelSanpham sanpham = sanphamList.get(i);
            int id = sanpham.getId(); // Lấy ID từ đối tượng ModelSanpham
            idArray[i] = id; // Thêm ID vào mảng
            Log.e("id", String.valueOf(id));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyc_giohang.setLayoutManager(layoutManager);
        giohangAdapter = new GiohangAdapter(this,sanphamList);
        recyc_giohang.setAdapter(giohangAdapter);

        if(sanphamList.isEmpty()){
            giohangtrong.setVisibility(View.VISIBLE);
            recyc_giohang.setVisibility(View.INVISIBLE);
            muahang.setVisibility(View.GONE);
        }else{
            giohangtrong.setVisibility(View.GONE);
            recyc_giohang.setVisibility(View.VISIBLE);
            muahang.setVisibility(View.VISIBLE);
        }

        giohangAdapter.notifyDataSetChanged();
    }
}