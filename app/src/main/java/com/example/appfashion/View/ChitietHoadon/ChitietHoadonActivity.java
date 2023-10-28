package com.example.appfashion.View.ChitietHoadon;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfashion.Adapter.ChitietHoadonAdapter;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Presenter.ChitietHoadon.PresenterChitietHoadon;
import com.example.appfashion.R;

import java.util.List;
import java.util.Objects;

public class ChitietHoadonActivity extends AppCompatActivity implements ViewChitietHoadon{
    Toolbar toolbar;
    RecyclerView recyc_chitiethoadon;
    PresenterChitietHoadon presenterChitietHoadon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_hoadon);
        Anhxa();
        ActionToolBar();
        setStatusBarColor();
        Intent intent = getIntent();
        int idhoadon = intent.getIntExtra("idhoadon", -1);
        presenterChitietHoadon.getDataChitietHoadon(this,idhoadon);
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

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar_chitiethoadon);
        recyc_chitiethoadon = findViewById(R.id.recyc_chitiethoadon);
        presenterChitietHoadon = new PresenterChitietHoadon(this);
    }

    @Override
    public void ShowListChitietHoadonSuccess(List<ModelSanpham> sanphamList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ChitietHoadonAdapter chitietHoadonAdapter = new ChitietHoadonAdapter(ChitietHoadonActivity.this,sanphamList);
        recyc_chitiethoadon.setLayoutManager(layoutManager);
        recyc_chitiethoadon.setAdapter(chitietHoadonAdapter);
        chitietHoadonAdapter.notifyDataSetChanged();
    }

    @Override
    public void Loitruycap(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}