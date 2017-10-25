package com.example.bottomnabar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwei.okhttp3ps.app.MyApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 曹少航 on 2017/10/24.
 */

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {
    private List<Bean.DataBean> list;
    private Context context;

    public MyItemAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bean.DataBean dataBean = list.get(position);
      //  Picasso.with(context).load(dataBean.getMiddle_image().getUrl()).into(holder.image1);
        ImageLoader.getInstance().displayImage(dataBean.getMiddle_image().getUrl(),holder.image1, MyApp.getImageOptions());
        holder.tv1.setText(dataBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView image1;
        private final TextView tv1;

        public MyViewHolder(View itemView) {
          super(itemView);
            image1 = itemView.findViewById(R.id.image1);
            tv1 = itemView.findViewById(R.id.tv1);
      }
  }

}
