package com.example.csh_demo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.csh_demo1.bean.MBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.csh_demo1.R.id.tv1;

/**
 * Created by 曹少航 on 2017/11/4.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<MBean> list;
    private  TextView tv;
    public MyRecyclerAdapter(Context context, List<MBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item1,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      // Picasso.with(context).load(list.get(position).getData().get(position).getImg());
        tv.setText(list.get(position).getData().get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class   MyViewHolder extends  RecyclerView.ViewHolder{

        private final ImageView image;


        public MyViewHolder(View itemView) {
          super(itemView);
            image = itemView.findViewById(R.id.image1);
            tv = itemView.findViewById(tv1);

      }
  }
}
