package com.example.appfashion.View.Sanpham;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfashion.Adapter.MenuAdapter;
import com.example.appfashion.Adapter.SanphamAdapter;
import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Presenter.DetailProduct.PresenterDetailProduct;
import com.example.appfashion.Presenter.Sanpham.PresenterSanpham;
import com.example.appfashion.R;
import com.example.appfashion.View.Giohang.GiohangActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SanphamActivity extends AppCompatActivity implements ViewSanpham{
    LinearLayout linear_spinner;
    Spinner spinner_phanloai;
    SanphamAdapter sanphamAdapter;
    MenuAdapter menuAdapter;
    RecyclerView recyc_menu;
    RecyclerView recycSp;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar_sp;
    int idloaisp=0;
    String tenloaisp;
    PresenterSanpham presenterSanpham;
    TextView txtGiohang;
    boolean onPause = false;
    List<String> phanloaiList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);
        Anhxa();
        ActionBar();
        Intent intent = getIntent();
        idloaisp = Integer.parseInt(intent.getStringExtra("idloaisp"));
        tenloaisp = intent.getStringExtra("tenloaisp");
        phanloaiList.add("Tất cả sản phẩm");

        presenterSanpham.getLoaisp();
        EventSpinner();
        setColorThanhdieuhuong();
        toolbar_sp.setTitle(tenloaisp);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void EventSpinner() {
        if(tenloaisp.equals("Áo Thun")||tenloaisp.equals("Áo Khoác")||tenloaisp.equals("Áo Sơ Mi")||tenloaisp.equals("Phụ Kiện")){

            ArrayAdapter<String> adapter = new ArrayAdapter<>(SanphamActivity.this, android.R.layout.simple_spinner_item, phanloaiList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_phanloai.setAdapter(adapter);
            spinner_phanloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedPhanloai = parent.getItemAtPosition(position).toString().trim();
                    if(selectedPhanloai.equals("Tất cả sản phẩm")){
                        presenterSanpham.getSp(idloaisp);
                    }else{
                        presenterSanpham.getDataPhanloai(selectedPhanloai);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else{
            presenterSanpham.getSp(idloaisp);
            linear_spinner.setVisibility(View.GONE);
        }
    }

    private void setColorThanhdieuhuong(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onPause){
            PresenterDetailProduct presenterDetailProduct = new PresenterDetailProduct();
            txtGiohang.setText(String.valueOf(presenterDetailProduct.DemSoLuongSanphamTrongGiohang(this)));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    private void ActionBar() {
        setSupportActionBar(toolbar_sp);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar_sp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sp,menu);
        MenuItem iGiohang = menu.findItem(R.id.menu_giohang);
        View viewCustomGiohang = MenuItemCompat.getActionView(iGiohang);
        viewCustomGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanphamActivity.this, GiohangActivity.class);
                startActivity(intent);

            }
        });
        PresenterDetailProduct presenterDetailProduct = new PresenterDetailProduct();
        txtGiohang = viewCustomGiohang.findViewById(R.id.txtSlSP);
        txtGiohang.setText(String.valueOf(presenterDetailProduct.DemSoLuongSanphamTrongGiohang(this)));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_nav) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        if (item.getItemId() == R.id.menu_giohang) {
            Intent i = new Intent(SanphamActivity.this, GiohangActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Anhxa() {
        linear_spinner = findViewById(R.id.linear_spinner);
        spinner_phanloai = findViewById(R.id.spinner_phanloai);
        recyc_menu = findViewById(R.id.recyc_menu);
        toolbar_sp = findViewById(R.id.toolbar_sp);
        drawerLayout = findViewById(R.id.drawerLayout);
        recycSp = findViewById(R.id.recycSp);
        presenterSanpham = new PresenterSanpham(this);
    }

    @Override
    public void ShowListSpSuccess(List<ModelSanpham> sanphamList) {


        for(int i=0;i<sanphamList.size();i++){
            String phanloai = sanphamList.get(i).getPhanloai();
            if(!phanloaiList.contains(phanloai)){
                phanloaiList.add(phanloai);
            }
        }
        Log.e("phanloaulust", String.valueOf(phanloaiList));

        LinearLayoutManager layoutManager = new GridLayoutManager(SanphamActivity.this,2);
        recycSp.setLayoutManager(layoutManager);
        sanphamAdapter = new SanphamAdapter(SanphamActivity.this,sanphamList);
        recycSp.setAdapter(sanphamAdapter);
        sanphamAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShowListLoaiSpSuccess(List<ModelLoaiSanPham> loaiSanPhams) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyc_menu.setLayoutManager(layoutManager);
        menuAdapter = new MenuAdapter(SanphamActivity.this,loaiSanPhams);
        recyc_menu.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }


    @Override
    public void Loitruycap(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

}