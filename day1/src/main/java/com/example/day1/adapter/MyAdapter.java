package com.example.day1.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.day1.BannerImageLoader;
import com.example.day1.R;
import com.example.day1.bean.Bean;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹少航 on 2017/10/31.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Bean.StoriesBean> storiesBeanList;
    private List<Bean.TopStoriesBean> topStoriesBeanList;

    public MyAdapter(Context context, List<Bean.StoriesBean> storiesBeanList, List<Bean.TopStoriesBean> topStoriesBeanList) {
        this.context = context;
        this.storiesBeanList = storiesBeanList;
        this.topStoriesBeanList = topStoriesBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else if(position==1){
            return 1;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(context).inflate(R.layout.item1, parent, false);
            BannerViewHolder bannerViewHolder=new BannerViewHolder(view);
            return  bannerViewHolder;
        }else {
            View view1=LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
            RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view1);
            return recyclerViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(holder instanceof  BannerViewHolder){
              BannerViewHolder holder1= (BannerViewHolder) holder;
              holder1.banner.setImageLoader(new BannerImageLoader());
              List<String> bannerList=new ArrayList<>();
              for (int i=0;i<topStoriesBeanList.size();i++){
                  bannerList.add(topStoriesBeanList.get(i).getImage());
              }
              holder1.banner.setImages(bannerList);
              holder1.banner.setDelayTime(3000);
              holder1.banner.start();

          }else  if(holder instanceof  RecyclerViewHolder){
              RecyclerViewHolder holder2= (RecyclerViewHolder) holder;
              holder2.rv_new.setLayoutManager(new LinearLayoutManager(context));
              MyRecyNewAdapter madapter=new MyRecyNewAdapter(context,storiesBeanList);
                holder2.rv_new.setAdapter(madapter);
          }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
    //定义加载更多的方法
    public void loadMore(List<Bean.StoriesBean> s){
        for (Bean.StoriesBean str:s){
            this.storiesBeanList.add(str);
        }
        //更新界面
        notifyDataSetChanged();
    }
    //刷新
    public void refreshMore(List<Bean.StoriesBean> s){
        for (Bean.StoriesBean str:s) {
            this.storiesBeanList.add(0,str);
        }
        //更新界面
        notifyDataSetChanged();
    }



    public class RecyclerViewHolder extends  RecyclerView.ViewHolder{

        private final RecyclerView rv_new;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            rv_new = itemView.findViewById(R.id.rv_new);

        }
    }
    public class BannerViewHolder extends RecyclerView.ViewHolder{

        private final Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

}
