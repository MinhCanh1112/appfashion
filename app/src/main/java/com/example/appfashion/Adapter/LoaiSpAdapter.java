package com.example.appfashion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfashion.R;
import com.example.appfashion.View.Sanpham.SanphamActivity;
import com.example.appfashion.Model.Home.ModelLoaiSanPham;
import com.example.appfashion.Util.Utils;

import java.util.List;

public class LoaiSpAdapter extends RecyclerView.Adapter<LoaiSpAdapter.MyViewHolder> {
    Context context;
    List<ModelLoaiSanPham> loaiSanPhams;

    public LoaiSpAdapter(Context context, List<ModelLoaiSanPham> loaiSanPhams) {
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;
    }

    @NonNull
    @Override
    public LoaiSpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_loaisp,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSpAdapter.MyViewHolder holder, int position) {
        ModelLoaiSanPham loaiSanPham = loaiSanPhams.get(position);
        holder.name.setText(loaiSanPham.getTenloaisp());
        Glide.with(context).load(Utils.BaseUrl +"hinhanh/loaisanpham/" +loaiSanPham.getHinhanhloaisp()).into(holder.image);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SanphamActivity.class);
                intent.putExtra("idloaisp",loaiSanPham.getIdloaisp());
                intent.putExtra("tenloaisp",loaiSanPham.getTenloaisp());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiSanPhams.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            image = itemView.findViewById(R.id.item_imageloaisp);
            name = itemView.findViewById(R.id.item_loaisp);
        }
    }
}
