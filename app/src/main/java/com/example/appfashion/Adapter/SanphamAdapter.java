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
import com.example.appfashion.View.DetailProduct.DetailActivity;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Util.Utils;

import java.text.DecimalFormat;
import java.util.List;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.MyHolderView> {
    Context context;
    List<ModelSanpham> sanphamList;

    public SanphamAdapter(Context context, List<ModelSanpham> sanphamList) {
        this.context = context;
        this.sanphamList = sanphamList;
    }

    public void setFilteredList(List<ModelSanpham> filteredList){
        this.sanphamList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SanphamAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_sp,parent,false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanphamAdapter.MyHolderView holder, int position) {
        ModelSanpham sanpham = sanphamList.get(position);
        holder.item_name.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_price.setText("Giá: "+decimalFormat.format(sanpham.getGia()) + " đ");
        Glide.with(context).load(Utils.BaseUrl+"hinhanh/sanpham/all/" +sanpham.getHinhanh1()).into(holder.item_image);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("dataSanpham",sanpham);
                intent.putExtra("idsp",sanpham.getIdsp());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanphamList != null ? sanphamList.size():0;
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name,item_price;
        View view;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            item_image = itemView.findViewById(R.id.item_image);
            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
        }
    }
}
