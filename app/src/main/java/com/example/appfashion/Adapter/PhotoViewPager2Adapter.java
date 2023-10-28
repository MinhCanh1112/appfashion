package com.example.appfashion.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.R;

import java.util.List;

public class PhotoViewPager2Adapter extends RecyclerView.Adapter<PhotoViewPager2Adapter.MyholderView> {
    List<ModelSanpham> list;

    public PhotoViewPager2Adapter(List<ModelSanpham> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PhotoViewPager2Adapter.MyholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_photo,parent,false);
        return new MyholderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewPager2Adapter.MyholderView holder, int position) {
        ModelSanpham sanpham = list.get(position);
        if(sanpham == null){
            return;
        }
        Glide.with(holder.imageView.getContext()).load(sanpham.getHinhanh1()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list !=null?list.size():0;
    }

    public class MyholderView extends RecyclerView.ViewHolder {
        ImageView imageView ;
        public MyholderView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_photo);
        }
    }



}
