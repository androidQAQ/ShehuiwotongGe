package com.bw.csh_item.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bw.csh_item.R;

import com.bw.csh_item.bean.Bean1;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by 曹少航 on 2017/10/20.
 */

public class MyMainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Banner banner;
    private List<String> list=new ArrayList<>();
    private GridView  gv_fragment;
    private List<String> listT = new ArrayList<>();
    private List<Integer> imgT = new ArrayList<>();

    public MyMainRecyclerAdapter(Context context) {
        this.context = context;
    }

    public static  final  int type1=0;
    public static  final  int type2=1;
    public static  final  int type3=2;

    //判断加载的viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     if(viewType==type1){
         View view= LayoutInflater.from(context).inflate(R.layout.main_ba,parent,false);

         MyViewholer1 holder1=new MyViewholer1(view);

         return holder1;

     }else if(viewType==type2){
         View view= LayoutInflater.from(context).inflate(R.layout.main_grid,parent,false);

         MyViewholer2 holder2=new MyViewholer2(view);

         return holder2;

     }else if(viewType==type3){
         View view= LayoutInflater.from(context).inflate(R.layout.main_recycler,parent,false);

         MyViewholer3 holder3=new MyViewholer3(view);

         return holder3;
     }
        return null;

    }
    //给每个条目设置数据
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        int type=getItemViewType(position);
        switch (type){
            case  type1:
                //轮播方法
                getimage();
                break;
            case  type2:
                //设置图片
                listT.add("天猫");
                listT.add("聚划算");
                listT.add("天猫国际");
                listT.add("外卖");
                listT.add("天猫超市");
                listT.add("充值中心");
                listT.add("飞猪旅行");
                listT.add("领金币");
                listT.add("拍卖");
                listT.add("分类");
                imgT.add(R.mipmap.a);
                imgT.add(R.mipmap.b);
                imgT.add(R.mipmap.c);
                imgT.add(R.mipmap.d);
                imgT.add(R.mipmap.e);
                imgT.add(R.mipmap.f);
                imgT.add(R.mipmap.g);
                imgT.add(R.mipmap.h);
                imgT.add(R.mipmap.i);
                imgT.add(R.mipmap.j);
                MyGridAdapter myGridAdapter = new MyGridAdapter(listT,imgT,context);
                gv_fragment.setAdapter(myGridAdapter);
                break;

            case  type3:
                //RecyclerView
            if(holder instanceof  MyViewholer3){
                String url = "http://169.254.131.247/mobile/index.php?act=goods&op=goods_detail&%20goods_id=100009";
                ((MyViewholer3) holder).recyc.setLayoutManager(new GridLayoutManager(context,2));
                OkHttp3Utils.doGet(url, new GsonObjectCallback<Bean1>() {
                    @Override
                    public void onUi(Bean1 bean1) {
                        List<Bean1.DatasBean.GoodsCommendListBean> list = bean1.getDatas().getGoods_commend_list();

                        MyMainGridAadpter adapter_m= new MyMainGridAadpter(list,context);

                        ((MyViewholer3) holder).recyc.setAdapter(adapter_m);
                    }
                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });

            }
                break;
        }

    }
    @Override
    public int getItemCount() {
        return 3;
    }

    class MyViewholer1 extends  RecyclerView.ViewHolder{

        public MyViewholer1(View itemView) {
            super(itemView);
          banner= (Banner) itemView.findViewById(R.id.banner);
        }
    }
    private void getimage() {
        //banner轮播
        list.add("http://169.254.51.175/data/upload/shop/store/goods/1/1_04752627958339099_360.jpg");
        list.add("http://169.254.51.175/data/upload/shop/store/goods/1/1_04752627958339099_360.jpg");
        banner.setImageLoader(new MyApplication());
        banner.setImages(list);
        banner.setDelayTime(2000);
        banner.start();
    }
    class MyViewholer2 extends  RecyclerView.ViewHolder{

        public MyViewholer2(View itemView) {
            super(itemView);
            gv_fragment = (GridView) itemView.findViewById(R.id.gv_fragment);
        }
    }
    class MyViewholer3 extends  RecyclerView.ViewHolder{

        private RecyclerView recyc;

        public MyViewholer3(View itemView) {
            super(itemView);
        recyc= (RecyclerView) itemView.findViewById(R.id.recyc);
        }
    }

    //判断加载那个布局
    @Override
    public int getItemViewType(int position) {
       if(position==0){
           return type1;
       }else if(position==1){
           return type2;
       }else {
           return type3;
       }
    }
}
