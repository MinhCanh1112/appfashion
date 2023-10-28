package com.example.appfashion.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.appfashion.Model.Home.ModelQuangcao;
import com.example.appfashion.R;
import com.example.appfashion.Util.Utils;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
    Context context;
    List<ModelQuangcao> quangcaoArrayList;

    public BannerAdapter(Context context, List<ModelQuangcao> quangcaoArrayList) {
        this.context = context;
        this.quangcaoArrayList = quangcaoArrayList;
    }

    @Override
    public int getCount() {
        return quangcaoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_banner,container,false);
        ImageView img_banner = view.findViewById(R.id.img_banner);
        Glide.with(context).load(Utils.BaseUrl+"hinhanh/quangcao/" +quangcaoArrayList.get(position).getHinhanh()).into(img_banner);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

