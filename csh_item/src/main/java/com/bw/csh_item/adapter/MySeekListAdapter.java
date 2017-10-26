package com.bw.csh_item.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.csh_item.R;
import com.bw.csh_item.bean.SeekListBean;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by 曹少航 on 2017/10/18.
 */

public class MySeekListAdapter extends RecyclerView.Adapter<MySeekListAdapter.MyViewHolder> {

   private List<SeekListBean.DatasBean.GoodsListBean> goods_list;
    private Context context;

    public MySeekListAdapter(List<SeekListBean.DatasBean.GoodsListBean> goods_list, Context context) {
        this.goods_list = goods_list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.item_seek,parent,false);
            final MyViewHolder holder=new MyViewHolder(v);

        //放在OnCreateViewHolder
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });

             return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //設置圖片
        Picasso.with(context).load(goods_list.get(position).getGoods_image_url()).into(holder.image_seek);
        //設置字體
        holder.tv_seek.setText(goods_list.get(position).getGoods_name());


    }

    @Override
    public int getItemCount() {
        return goods_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private  ImageView image_seek;
        private  TextView tv_seek;

        public MyViewHolder(View itemView) {
            super(itemView);
           image_seek= (ImageView) itemView.findViewById(R.id.image_seek);
            tv_seek= (TextView) itemView.findViewById(R.id.tv_seek);
        }

    }
    //接口回调
             private OnItemClickListener listener;

             public interface OnItemClickListener {
                 void onItemClick(int position);
             }
             public void setOnItemClickListener(OnItemClickListener listener) {
                 this.listener = listener;
             }

}
