package com.example.caoshaohang1507e.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bwei.okhttp3ps.app.MyApp;
import com.example.caoshaohang1507e.R;
import com.example.caoshaohang1507e.bean.DetailsBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

import static android.R.id.list;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<DetailsBean.SongListBean> song_list;

    public MyAdapter(Context context, List<DetailsBean.SongListBean> song_list) {
        this.context = context;
        this.song_list = song_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //获取数据
        DetailsBean.SongListBean songListBean = song_list.get(position);
        //设置
        ImageLoader.getInstance().displayImage(songListBean.getPic_small(),holder.image1, MyApp.getImageOptions());
        //设置文本
        holder.tv1.setText(songListBean.getTitle());

    }
    @Override
    public int getItemCount() {
        return song_list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private final ImageView image1;
        private final TextView tv1;
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
