package com.example.appfashion.View.Home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appfashion.Adapter.BannerAdapter;
import com.example.appfashion.Adapter.LoaiSpAdapter;
import com.example.appfashion.Adapter.SanphamAdapter;
import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Model.Home.ModelQuangcao;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Presenter.DetailProduct.PresenterDetailProduct;
import com.example.appfashion.Presenter.Home.PresenterHome;
import com.example.appfashion.R;
import com.example.appfashion.View.Giohang.GiohangActivity;
import com.example.appfashion.View.Profile.ProfileActivity;
import com.example.appfashion.View.Search.SearchActivity;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends AppCompatActivity implements ViewHome {
    RecyclerView recycAllsp;
    SanphamAdapter sanphamAdapter;
    TextView txtGiohang;
    long mBackPressed;
    RecyclerView recyc_loaisp;
    LoaiSpAdapter loaiSpAdapter ;
    Toolbar toolbar;
    ViewPager viewPager;
    CircleIndicator circleIndicatorBanner;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    PresenterHome presenterHome;
    boolean onPause = false;
    public static List<ModelSanpham> sanphams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Anhxa();

        presenterHome = new PresenterHome(this);
        presenterHome.getDataSp();
        presenterHome.getDataLoaiSp();
        presenterHome.getDataBanner();

        setColorThanhdieuhuong();
        setSupportActionBar(toolbar);

    }

    private void setColorThanhdieuhuong() {
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

    @Override
    public void onBackPressed() {
        if(mBackPressed +2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(), "Nhấn Back lần nữa để đăng xuất", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();

    }

    private void Anhxa(){
        viewPager = findViewById(R.id.viewPager);
        circleIndicatorBanner = findViewById(R.id.circleIndicatorBanner);

        toolbar = findViewById(R.id.toolbar_home);

        recycAllsp = findViewById(R.id.recycAllsp);
        recycAllsp.setNestedScrollingEnabled(false);

        recyc_loaisp = findViewById(R.id.recyc_loaisp);
        recyc_loaisp.setNestedScrollingEnabled(false);

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);

        MenuItem iGiohang = menu.findItem(R.id.menuhome_giohang);
        View viewCustomGiohang = MenuItemCompat.getActionView(iGiohang);

        viewCustomGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GiohangActivity.class);
                startActivity(intent);
            }
        });
        txtGiohang = viewCustomGiohang.findViewById(R.id.txtSlSP);
        PresenterDetailProduct presenterDetailProduct = new PresenterDetailProduct();
        txtGiohang.setText(String.valueOf(presenterDetailProduct.DemSoLuongSanphamTrongGiohang(this)));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuhome_user) {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.menuhome_search) {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void ShowListSpSuccess(List<ModelSanpham> sanphamList) {
        sanphams = sanphamList;
        LinearLayoutManager layoutManager = new GridLayoutManager(HomeActivity.this,2);
        recycAllsp.setLayoutManager(layoutManager);
        sanphamAdapter = new SanphamAdapter(HomeActivity.this,sanphamList);
        recycAllsp.setAdapter(sanphamAdapter);
        sanphamAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShowListSpFail(Throwable t) {
        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("ViewListSp",t.getMessage());
    }

    @Override
    public void ShowListLoaispSuccess(List<ModelLoaiSanPham> loaiSanPhamList) {
        LinearLayoutManager layoutManager = new GridLayoutManager(HomeActivity.this,5);
        recyc_loaisp.setLayoutManager(layoutManager);
        loaiSpAdapter = new LoaiSpAdapter(HomeActivity.this,loaiSanPhamList);
        recyc_loaisp.setAdapter(loaiSpAdapter);
        loaiSpAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShowListLoaispFail(Throwable t) {
        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("ViewLoaisp",t.getMessage());
    }

    @Override
    public void ShowListBannerSuccess(List<ModelQuangcao> quangcaoList) {
        bannerAdapter = new BannerAdapter(HomeActivity.this,quangcaoList);
        viewPager.setAdapter(bannerAdapter);
        circleIndicatorBanner.setViewPager(viewPager);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentItem = viewPager.getCurrentItem();
                currentItem++;
                if(currentItem>=viewPager.getAdapter().getCount()){
                    currentItem = 0;
                }

                viewPager.setCurrentItem(currentItem,true);
                handler.postDelayed(runnable,4500);
            }
        };
        handler.postDelayed(runnable,4500);
    }

    @Override
    public void ShowListBannerFail(Throwable t) {
        Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("ViewBanner",t.getMessage());
    }
}