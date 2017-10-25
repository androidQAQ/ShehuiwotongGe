package com.example.bottomnabar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/24.
 */

public class MyRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  Banner banner;

    private Context context;
    private List<String> list=new ArrayList<>();


    public MyRecyclerView(Context context) {
        this.context = context;

    }
    private static  final  int type1=1;
    private static  final  int type2=2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==type1){
            View view1= LayoutInflater.from(context).inflate(R.layout.recycler_one,null);
            MyViewHolder1 holder1=new MyViewHolder1(view1);
            return  holder1;
        }else  if (viewType==type2){
            View view2= LayoutInflater.from(context).inflate(R.layout.reycyler_pub,null);
            MyViewHolder2 holder2=new MyViewHolder2(view2);
            return  holder2;
        }


        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type){
            case  type1:
                getimage();
                break;
            case  type2:


                String url="http://ic.snssdk.com/2/article/v25/stream/?category=news_entertainment&count=20&min_behot_time=1455522338&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";

                OkHttp3Utils.doGet(url, new GsonObjectCallback<Bean>() {
                    @Override
                    public void onUi(Bean bean) {
                        List<Bean.DataBean> data = bean.getData();
                        ((MyViewHolder2)holder).recyeler_item.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

                        MyItemAdapter adapter =new MyItemAdapter(data,context);
                        ((MyViewHolder2)holder).recyeler_item.setAdapter(adapter);

                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });

                break;

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    //判断加载那个条目
    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return type1;
        }else if(position==1){
            return type2;
        }else {
            return position;
        }

    }

    //自己创建两个ViewHolder
    class MyViewHolder1 extends RecyclerView.ViewHolder{

        public MyViewHolder1(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.ban);
        }
    }
    private void getimage() {
            list.add("http://img5.imgtn.bdimg.com/it/u=2459711867,2738074318&fm=11&gp=0.jpg");
            list.add("http://img2.imgtn.bdimg.com/it/u=24683053,3924714235&fm=11&gp=0.jpg");
            list.add("http://img1.imgtn.bdimg.com/it/u=911961611,4197340266&fm=11&gp=0.jpg");
            banner.setImageLoader(new Myimageloader());
            banner.setImages(list);
            banner.setDelayTime(2000);
            banner.start();
        }

    class MyViewHolder2 extends RecyclerView.ViewHolder{
        private  RecyclerView recyeler_item;
        public MyViewHolder2(View itemView) {
            super(itemView);
            recyeler_item = itemView.findViewById(R.id.recycler_item);

        }
    }


}
