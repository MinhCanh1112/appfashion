package com.example.appfashion.Adapter;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfashion.Model.Giohang.ModelGiohang;
import com.example.appfashion.Model.Sanpham.ModelSanpham;
import com.example.appfashion.Presenter.Giohang.PresenterGiohang;
import com.example.appfashion.R;
import com.example.appfashion.View.DetailProduct.DetailActivity;
import com.example.appfashion.View.Giohang.GiohangActivity;

import java.text.DecimalFormat;
import java.util.List;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.MyViewHolder> {
    GiohangActivity context;
    List<ModelSanpham> sanphamList;


    public GiohangAdapter(GiohangActivity context, List<ModelSanpham> sanphamList) {
        this.context = context;
        this.sanphamList = sanphamList;
    }

    public List<ModelSanpham> getSanphamList() {
        return sanphamList;
    }

    @NonNull
    @Override
    public GiohangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_giohang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiohangAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelSanpham sanpham = sanphamList.get(position);
        ModelGiohang modelGiohang = new ModelGiohang();
        modelGiohang.MoKetNoiSQL(context);

        holder.txtname.setText(sanpham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String gia = decimalFormat.format(sanpham.getGia())+"đ";
        holder.txtgia.setText(gia);
        holder.txtsl.setText(String.valueOf(sanpham.getSoluong()));
        holder.txtsoluongx.setText(" + "+sanpham.getSoluong()+" = ");
        int tongtiensp = sanpham.getGia()*sanpham.getSoluong();
        String sumsp = decimalFormat.format(tongtiensp) +"đ";
        holder.txtgiaxsoluong.setText(sumsp);
        if(sanpham.getSize().equals("-1")){
            holder.txtsize.setVisibility(View.GONE);
        }
        holder.txtsize.setText("Size: "+sanpham.getSize());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("idsp",sanpham.getIdsp());
                context.startActivity(intent);
            }
        });

        Glide.with(context).load(sanpham.getHinhgiohang()).into(holder.imgSp);

        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có muốn xóa sản phẩm này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modelGiohang.XoaSanphamTrongGiohang(sanpham.getId());
                        PresenterGiohang presenterGiohang = new PresenterGiohang(context);
                        presenterGiohang.getDataGiohang(context);
                        sanphamList.remove(position);
                        notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        holder.imgcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtsl.getText().toString());
                soluong++;

                holder.txtsl.setText(String.valueOf(soluong));
                modelGiohang.UpdateSoluongSpGiohang(sanpham.getId(),soluong);
                if(soluong>1){
                    holder.txtsoluongx.setText(" + "+soluong+" = ");
                    int tongtiensp = sanpham.getGia()*soluong;
                    String sumsp = decimalFormat.format(tongtiensp) +"đ";
                    holder.txtgiaxsoluong.setText(sumsp);
                }
//                notifyDataSetChanged();
            }
        });

        holder.imgtru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtsl.getText().toString());
                if(soluong>1){
                    soluong--;
                    holder.txtsoluongx.setText(" + "+soluong+" = ");
                    int tongtiensp = sanpham.getGia()*soluong;
                    String sumsp = decimalFormat.format(tongtiensp) +"đ";
                    holder.txtgiaxsoluong.setText(sumsp);
                }
                holder.txtsl.setText(String.valueOf(soluong));
                modelGiohang.UpdateSoluongSpGiohang(sanpham.getId(),soluong);
//                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanphamList!=null?sanphamList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSp,imgxoa,imgtru,imgcong;
        TextView txtname,txtgia,txtgiaxsoluong,txtsoluongx,txtsl,txtsize;
        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imgSp = itemView.findViewById(R.id.imgSPGiohang);
            txtname = itemView.findViewById(R.id.txtNameSP);
            txtgia = itemView.findViewById(R.id.one_price);
            txtsoluongx = itemView.findViewById(R.id.one_xsoluong);
            txtgiaxsoluong = itemView.findViewById(R.id.one_giaxsoluong);
            txtsl = itemView.findViewById(R.id.one_sl);
            txtsize = itemView.findViewById(R.id.one_size);
            imgxoa = itemView.findViewById(R.id.xoasp);
            imgcong = itemView.findViewById(R.id.cong);
            imgtru = itemView.findViewById(R.id.tru);
        }
    }
}
