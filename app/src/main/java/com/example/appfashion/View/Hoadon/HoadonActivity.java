package com.example.appfashion.View.Hoadon;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfashion.Adapter.HoadonAdapter;
import com.example.appfashion.Model.Hoadon.ModelHoadon;
import com.example.appfashion.Presenter.Hoadon.PresenterHoadon;
import com.example.appfashion.R;

import java.util.List;
import java.util.Objects;

public class HoadonActivity extends AppCompatActivity implements ViewHoadon{
    PresenterHoadon presenterHoadon;
    Toolbar toolbar;
    RecyclerView recyc_hoadon;
    HoadonAdapter hoadonAddapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        Anhxa();
        ActionToolBar();
        setStatusBarColor();
        presenterHoadon.getDataHoadon(this);

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
        presenterHoadon = new PresenterHoadon(this);
        toolbar = findViewById(R.id.toolbar_hoadon);
        recyc_hoadon = findViewById(R.id.recyc_hoadon);
    }


    @Override
    public void ShowListHoadonSuccess(List<ModelHoadon> hoadonList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        hoadonAddapter = new HoadonAdapter(HoadonActivity.this,hoadonList);
        recyc_hoadon.setLayoutManager(layoutManager);
        recyc_hoadon.setAdapter(hoadonAddapter);
        hoadonAddapter.notifyDataSetChanged();
    }

    @Override
    public void Loitruycap(Throwable t) {

    }
}