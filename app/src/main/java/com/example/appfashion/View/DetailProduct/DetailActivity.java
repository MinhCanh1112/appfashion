package com.example.appfashion.View.DetailProduct;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.appfashion.Adapter.PhotoViewPager2Adapter;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Presenter.DetailProduct.PresenterDetailProduct;
import com.example.appfashion.R;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Giohang.GiohangActivity;
import com.example.appfashion.View.Thanhtoan.ThanhtoanActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator3;

public class DetailActivity extends AppCompatActivity implements ViewDetail{
    // bottom dialog
    ImageView bot_image,bot_x,bot_tru,bot_cong;
    TextView bot_name,bot_price,bot_sl;
    Spinner bot_spinner;
    AppCompatButton bot_muahang;
    LinearLayout linearbot_size;

    // -------------------------------- //
    TextView tensp_detail,giasp_detail,motadetail,txtGiohang;
    ImageView hinhanh7,hinhanh6,hinhanh5,hinhanhsize;
    ImageButton themgiohang;
    AppCompatButton btn_muahang;
    DecimalFormat decimalFormat;
    ModelSanpham sanpham;
    ViewPager2 viewPager2;
    CircleIndicator3 ci3;
    List<ModelSanpham> photoList = new ArrayList<>();
    int idsp = 0;
    Toolbar toolbar;
    PresenterDetailProduct presenterDetailProduct;
    PhotoViewPager2Adapter photoViewPager2Adapter;
    boolean onPause = false;
    int soluong,giasp;
    String size,namesp,hinhanh1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Anhxa();
        ActionBar();
//        sanpham = (ModelSanpham) getIntent().getSerializableExtra("dataSanpham");
        Intent intent = getIntent();
        idsp = intent.getIntExtra("idsp",-1);
//        idsp = sanpham.getIdsp();
        presenterDetailProduct.getDataSp(idsp);
//        setData();
//        setImageViewDetail();
        EventThemGioHang();
        EventMuahang();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void EventMuahang() {
        btn_muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(DetailActivity.this);
                dialog.setContentView(R.layout.dialog_muahang);
                AnhxaDiaglog(dialog);

                Glide.with(DetailActivity.this).load(Utils.BaseUrl+"hinhanh/sanpham/all/"+hinhanh1).into(bot_image);
                bot_name.setText(namesp);
                bot_price.setText(decimalFormat.format(giasp)+"đ");
                ChonSoluongSP(bot_cong,bot_tru,bot_sl);

                if(size == null||size.equals("-1")){
                    soluong = Integer.parseInt(bot_sl.getText().toString().trim());
                    linearbot_size.setVisibility(View.GONE);
                    muahang(bot_muahang,"-1",bot_sl);
                }else {

                    String[] sizes = size.split(",");
                    List<String> sizeList = Arrays.asList(sizes);
                    // Tạo ArrayAdapter và gán nó vào Spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(DetailActivity.this, android.R.layout.simple_spinner_item, sizeList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    bot_spinner.setAdapter(adapter);

                    bot_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // Lấy văn bản của mục được chọn
                            String selectedSize = parent.getItemAtPosition(position).toString();
                            // Hiển thị văn bản của mục được chọn trong TextView
                            soluong = Integer.parseInt(bot_sl.getText().toString().trim());
                            muahang(bot_muahang,selectedSize,bot_sl);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Không làm gì khi không có mục nào được chọn
                        }
                    });
                }


                bot_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void muahang(Button btn,String size,TextView tvsl){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ThanhtoanActivity.class);
                String hinhanh = photoList.get(0).getHinhanh1();
                int soluong = Integer.parseInt(tvsl.getText().toString().trim());
                intent.putExtra("hinhsp",hinhanh);
                intent.putExtra("idsp",idsp);
                intent.putExtra("tensp",namesp);
                intent.putExtra("size",size);
                intent.putExtra("giasp",giasp);
                intent.putExtra("soluong",soluong);
                startActivity(intent);
            }
        });

    }

    private void EventThemGioHang() {
        themgiohang.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(DetailActivity.this);
                dialog.setContentView(R.layout.dialog_muahang);
                AnhxaDiaglog(dialog);

                Glide.with(DetailActivity.this).load(Utils.BaseUrl+"hinhanh/sanpham/all/"+hinhanh1).into(bot_image);
                bot_name.setText(namesp);
                bot_price.setText(decimalFormat.format(giasp)+"đ");

                bot_muahang.setText("Thêm vào giỏ hàng");
                bot_muahang.setTextColor(Color.WHITE);
                bot_muahang.setTextSize(17);
                ChonSoluongSP(bot_cong,bot_tru,bot_sl);

                if(size == null||size.equals("-1")){
                    linearbot_size.setVisibility(View.GONE);
                    themgiohang(bot_muahang,"-1",bot_sl);
                }else {
                    String[] sizes = size.split(",");
                    List<String> sizeList = Arrays.asList(sizes);
                    // Tạo ArrayAdapter và gán nó vào Spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(DetailActivity.this, android.R.layout.simple_spinner_item, sizeList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    bot_spinner.setAdapter(adapter);

                    bot_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // Lấy văn bản của mục được chọn
                            String selectedSize = parent.getItemAtPosition(position).toString();
                            // Hiển thị văn bản của mục được chọn trong TextView
                            themgiohang(bot_muahang,selectedSize,bot_sl);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Không làm gì khi không có mục nào được chọn
                        }
                    });
                }

                bot_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    private void themgiohang(Button btn, String size, TextView tvsl){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hinhanh = photoList.get(0).getHinhanh1();
                int soluong = Integer.parseInt(tvsl.getText().toString().trim());
//                Toast.makeText(DetailActivity.this, hinhanh, Toast.LENGTH_SHORT).show();
                Log.e("hinhanh",hinhanh);
                Glide.with(DetailActivity.this)
                        .asBitmap()
                        .load(hinhanh)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                // Bitmap đã được tải thành công từ URL
                                Log.e("bitmap", String.valueOf(bitmap));
                                // Sử dụng bitmap ở đây
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                                byte [] hinhgiohang = byteArrayOutputStream.toByteArray();
                                sanpham.setSize(size);
                                sanpham.setHinhgiohang(hinhgiohang);
                                sanpham.setSoluong(soluong);
                                presenterDetailProduct.ThemSanpham(sanpham,DetailActivity.this);
                            }

                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                // Xử lý khi không thể tải hình ảnh thành công
                            }
                        });

            }
        });

    }

    private void ChonSoluongSP(ImageView cong,ImageView tru, TextView txtsoluong){
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong = Integer.parseInt(txtsoluong.getText().toString().trim());
                soluong++;
                txtsoluong.setText(String.valueOf(soluong));
            }
        });

        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong = Integer.parseInt(txtsoluong.getText().toString().trim());
                if(soluong>1)
                    soluong--;
                txtsoluong.setText(String.valueOf(soluong));
            }
        });

    }

    private void AnhxaDiaglog(BottomSheetDialog dialog){
        bot_image = dialog.findViewById(R.id.bot_image);
        bot_x = dialog.findViewById(R.id.bot_x);
        bot_tru = dialog.findViewById(R.id.bot_tru);
        bot_cong = dialog.findViewById(R.id.bot_cong);
        bot_sl = dialog.findViewById(R.id.bot_sl);
        bot_name = dialog.findViewById(R.id.bot_name);
        bot_price = dialog.findViewById(R.id.bot_price);
        bot_muahang = dialog.findViewById(R.id.bot_muahang);
        linearbot_size = dialog.findViewById(R.id.linearbot_size);
        bot_spinner = dialog.findViewById(R.id.bot_spinner);

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

    private void setData(ModelSanpham sanpham) {
        size = sanpham.getSize();
        namesp = sanpham.getTensp();
        giasp = sanpham.getGia();
        hinhanh1 = sanpham.getHinhanh1();

        tensp_detail.setText(namesp);
        giasp_detail.setText(decimalFormat.format(giasp)+" đ");
        motadetail.setText(sanpham.getMota());

        Glide.with(DetailActivity.this).load(Utils.BaseUrl+"hinhanh/sanpham/all/" +sanpham.getHinhanh5()).into(hinhanh5);
        Glide.with(DetailActivity.this).load(Utils.BaseUrl+"hinhanh/sanpham/all/" +sanpham.getHinhanh6()).into(hinhanh6);
        Glide.with(DetailActivity.this).load(Utils.BaseUrl+"hinhanh/sanpham/all/" +sanpham.getHinhanh7()).into(hinhanh7);
        Glide.with(DetailActivity.this).load(Utils.BaseUrl+"hinhanh/sanpham/all/" +sanpham.getHinhanhsize()).into(hinhanhsize);
    }

    private void setImageViewDetail(ModelSanpham sanpham) {
        String[] hinhsanphamArray = sanpham.getHinhanh2().split(",");
        List<String> hinhsanpham = Arrays.asList(hinhsanphamArray);

        for (String hinhsanphamItem : hinhsanpham) {
            photoList.add(new ModelSanpham(Utils.BaseUrl+"hinhanh/sanpham/all/" +hinhsanphamItem));
        }

        photoViewPager2Adapter = new PhotoViewPager2Adapter(photoList);
        viewPager2.setAdapter(photoViewPager2Adapter);
        ci3.setViewPager(viewPager2);
    }



    private void ActionBar(){
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        MenuItem iGiohang = menu.findItem(R.id.menudetail_giohang);
        View viewCustomGiohang = MenuItemCompat.getActionView(iGiohang);
        viewCustomGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, GiohangActivity.class);
                startActivity(intent);
            }
        });
        txtGiohang = viewCustomGiohang.findViewById(R.id.txtSlSP);
        txtGiohang.setText(String.valueOf(presenterDetailProduct.DemSoLuongSanphamTrongGiohang(this)));
        return super.onCreateOptionsMenu(menu);
    }


    private void Anhxa() {
        decimalFormat = new DecimalFormat("###,###,###");
        toolbar = findViewById(R.id.toolbar_detail);
        hinhanh7 = findViewById(R.id.hinhanh7);
        hinhanh6 = findViewById(R.id.hinhanh6);
        hinhanh5 = findViewById(R.id.hinhanh5);
        hinhanhsize = findViewById(R.id.hinhanhsize);
        tensp_detail = findViewById(R.id.tensp_detail);
        giasp_detail = findViewById(R.id.giasp_detail);
        motadetail = findViewById(R.id.motadetail);
        viewPager2 = findViewById(R.id.viewPager2);
        ci3 = findViewById(R.id.ci3);
        themgiohang = findViewById(R.id.themgiohang);
        btn_muahang = findViewById(R.id.muahang);
        presenterDetailProduct = new PresenterDetailProduct(this);
    }

    @Override
    public void ShowDataSanpham(ModelSanpham sanphams) {
        sanpham =sanphams;
        setData(sanphams);
        setImageViewDetail(sanphams);
    }

    @Override
    public void loitruycap(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ThemGiohangSuccess() {
        Toast.makeText(this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
        txtGiohang.setText(String.valueOf(presenterDetailProduct.DemSoLuongSanphamTrongGiohang(this)));
    }

    @Override
    public void ThemGiohangFail() {
        Toast.makeText(this, "Thêm giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
    }
}