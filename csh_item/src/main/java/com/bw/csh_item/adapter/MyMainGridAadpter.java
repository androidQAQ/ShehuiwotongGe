package com.bw.csh_item.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.csh_item.R;
import com.bw.csh_item.bean.Bean1;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by 曹少航 on 2017/10/20.
 */

public class MyMainGridAadpter extends RecyclerView.Adapter<MyMainGridAadpter.MyAdapter> {
    private List<Bean1.DatasBean.GoodsCommendListBean> list;
    private Context context;

    public MyMainGridAadpter(List<Bean1.DatasBean.GoodsCommendListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.fragment_grid,parent,false);
        MyAdapter adapter=new MyAdapter(v);


        return adapter;
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, int position) {
        Picasso.with(context).load(list.get(position).getGoods_image_url()).into(holder.image1);
        holder.tv_s.setText(list.get(position).getGoods_name());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class MyAdapter extends RecyclerView.ViewHolder{

        private final ImageView image1;
        private final TextView tv_s;

        public MyAdapter(View itemView) {
                super(itemView);
            image1 = (ImageView) itemView.findViewById(R.id.iv_grid);
            tv_s = (TextView) itemView.findViewById(R.id.tv_grid);

            }
        }
}
