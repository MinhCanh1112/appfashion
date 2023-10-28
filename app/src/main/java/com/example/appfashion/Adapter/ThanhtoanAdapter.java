package com.example.appfashion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.R;

import java.text.DecimalFormat;
import java.util.List;

public class ThanhtoanAdapter extends RecyclerView.Adapter<ThanhtoanAdapter.MyViewHolder> {
    Context context;
    List<ModelSanpham> sanphamList;

    public ThanhtoanAdapter(Context context, List<ModelSanpham> sanphamList) {
        this.context = context;
        this.sanphamList = sanphamList;
    }

    @NonNull
    @Override
    public ThanhtoanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_thanhtoan,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhtoanAdapter.MyViewHolder holder, int position) {
        ModelSanpham sanpham = sanphamList.get(position);
        holder.tensp.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String giasp = decimalFormat.format(sanpham.getGia()) +"đ";
        holder.giasp.setText(giasp);
        holder.soluongsp.setText(String.valueOf(sanpham.getSoluong()));
        Glide.with(context).load(sanpham.getHinhgiohang()).into(holder.imghinhsp);
        if(sanpham.getSize().equals("-1")||sanpham.getSize()==null){
            holder.size.setVisibility(View.GONE);
        }
        holder.size.setText("Size: "+sanpham.getSize()+", ");
    }

    @Override
    public int getItemCount() {
        return sanphamList!=null?sanphamList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhsp;
        TextView tensp,soluongsp,giasp,size;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsp = itemView.findViewById(R.id.img_sp_thanhtoan);
            tensp = itemView.findViewById(R.id.tv_sp_name);
            giasp = itemView.findViewById(R.id.tv_sp_gia);
            soluongsp = itemView.findViewById(R.id.tv_sp_sl);
            size = itemView.findViewById(R.id.tv_sp_size);

        }
    }
}
