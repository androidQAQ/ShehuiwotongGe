package com.example.csh_demo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/25.
 */

public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Bean.StoriesBean> stories;
    private  ImageView image1;
    private  TextView tv1;

    public MyAdapter(Context context, List<Bean.StoriesBean> stories) {
        this.context = context;
        this.stories = stories;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,null);
        final MyViewHolder holder=new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerListener.onRecycle(holder.getPosition());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Picasso.with(context).load(stories.get(position).getImages().get(0)).into(image1);
        tv1.setText(stories.get(position).getTitle());
    }
    @Override
    public int getItemCount() {
        return stories.size();
    }

    class MyViewHolder extends XRecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image1);
            tv1 = itemView.findViewById(R.id.tv1);

        }
    }

    /**
     * 自定义接口回调 设置监听
     */
    private OnRecyclerListener onRecyclerListener;

    public interface OnRecyclerListener{
        void onRecycle(int position);
    }

    public void setOnRecyclerListener(OnRecyclerListener onRecyclerListener) {
        this.onRecyclerListener = onRecyclerListener;
    }

}
