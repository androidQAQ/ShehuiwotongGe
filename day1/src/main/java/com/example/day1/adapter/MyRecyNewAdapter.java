package com.example.day1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day1.R;
import com.example.day1.bean.Bean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 曹少航 on 2017/10/31.
 */

public class MyRecyNewAdapter extends RecyclerView.Adapter<MyRecyNewAdapter.MyViewHolder> {
    private Context context;
    private List<Bean.StoriesBean> list;

    public MyRecyNewAdapter(Context context, List<Bean.StoriesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item3, parent, false);
        MyViewHolder holder=new MyViewHolder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        List<String> images=list.get(position).getImages();
        for(int i=0;i<images.size();i++){
            Picasso.with(context).load(images.get(i)).into(holder.iv);
        }
        holder.tv.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_rv);
            tv = itemView.findViewById(R.id.tv_rv);
        }
    }

}
