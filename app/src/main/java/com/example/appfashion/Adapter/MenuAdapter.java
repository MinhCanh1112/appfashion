package com.example.appfashion.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.R;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Sanpham.SanphamActivity;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    SanphamActivity context;
    List<ModelLoaiSanPham> loaiSanPhams;

    public MenuAdapter(SanphamActivity context, List<ModelLoaiSanPham> loaiSanPhams) {
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;
    }


    @NonNull
    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_menu,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {
        ModelLoaiSanPham loaiSanPham = loaiSanPhams.get(position);
        holder.txtTenLoaisp.setText(loaiSanPham.getTenloaisp());
        Glide.with(context).load(Utils.BaseUrl +"hinhanh/loaisanpham/" +loaiSanPham.getHinhanhloaisp()).into(holder.hinhloaisp);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SanphamActivity.class);
                intent.putExtra("idloaisp",loaiSanPham.getIdloaisp());
                intent.putExtra("tenloaisp",loaiSanPham.getTenloaisp());
                context.startActivity(intent);
                context.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiSanPhams != null ? loaiSanPhams.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenLoaisp;
        ImageView hinhloaisp;
        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenLoaisp = itemView.findViewById(R.id.txtTenLoaisp);
            hinhloaisp = itemView.findViewById(R.id.img_loaisp);
            view = itemView;
        }
    }
}
