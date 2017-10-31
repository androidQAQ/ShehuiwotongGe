package com.example.csh1031.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.csh1031.R;
import com.example.csh1031.bean.MBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 曹少航 on 2017/10/31.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<MBean.DataBean> data;

    public MyAdapter(Context context, List<MBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder holder=new MyViewHolder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Picasso.with(context).load(data.get(position).getMedia_info().getAvatar_url()).into(holder.image1);
        holder.tv1.setText(data.get(position).getMedia_info().getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class  MyViewHolder extends  RecyclerView.ViewHolder{

        private final TextView tv1;
        private final ImageView image1;

        public MyViewHolder(View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image1);
            tv1 = itemView.findViewById(R.id.tv1);

        }
    }
}
