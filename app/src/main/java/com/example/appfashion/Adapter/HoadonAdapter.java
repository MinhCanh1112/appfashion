package com.example.appfashion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfashion.Model.Hoadon.ModelHoadon;
import com.example.appfashion.R;
import com.example.appfashion.View.ChitietHoadon.ChitietHoadonActivity;

import java.text.DecimalFormat;
import java.util.List;

public class HoadonAdapter extends RecyclerView.Adapter<HoadonAdapter.MyViewHolder> {
    Context context;
    List<ModelHoadon> hoadonList;

    public HoadonAdapter(Context context, List<ModelHoadon> hoadonList) {
        this.context = context;
        this.hoadonList = hoadonList;
    }

    @NonNull
    @Override
    public HoadonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_hoadon,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoadonAdapter.MyViewHolder holder, int position) {
        ModelHoadon hoadon = hoadonList.get(position);
        holder.name.setText(hoadon.getName());
        holder.mobile.setText(hoadon.getMobile());
        holder.diachi.setText("Địa chỉ: "+hoadon.getDiachi());
        holder.datetime.setText("Thời gian đặt hàng: "+hoadon.getDatetime());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tongtien.setText("Tổng tiền: "+decimalFormat.format(hoadon.getTongtien())+"đ");
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChitietHoadonActivity.class);
                intent.putExtra("idhoadon",hoadon.getIdhoadon());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return hoadonList!=null?hoadonList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,mobile,diachi,datetime,tongtien;
        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.one1_name);
            mobile = itemView.findViewById(R.id.one1_mobile);
            diachi = itemView.findViewById(R.id.oneaddress);
            datetime = itemView.findViewById(R.id.onetime);
            tongtien = itemView.findViewById(R.id.onetongtien);
        }
    }
}
