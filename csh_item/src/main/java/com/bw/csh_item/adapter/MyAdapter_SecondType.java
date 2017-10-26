package com.bw.csh_item.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bw.csh_item.R;
import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.TypeSecondBean;
import com.bw.csh_item.bean.TypeThirdBean;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;


import java.io.IOException;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dell-pc on 2017/10/16.
 */

public class MyAdapter_SecondType extends RecyclerView.Adapter<MyAdapter_SecondType.MyViewHolder> {

    private List<TypeSecondBean.DatasBean.ClassListBean> list;
    private Context context;

    public MyAdapter_SecondType(List<TypeSecondBean.DatasBean.ClassListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_secondtype, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.type_gc_name.setText(list.get(position).getGc_name());
        holder.rv_third.setLayoutManager(new GridLayoutManager(context,3));
      //请求三级列表
        OkHttp3Utils.doGet(MyApi.FirstList + "&gc_id=" + list.get(position).getGc_id(), new GsonObjectCallback<TypeThirdBean>() {
            @Override
            public void onUi(TypeThirdBean typeThirdBean) {
                MyAdapter_ThirdType myAdapter_thirdType = new MyAdapter_ThirdType(typeThirdBean.getDatas().getClass_list(),context);
                holder.rv_third.setAdapter(myAdapter_thirdType);
            }
            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView type_gc_name;
        private final RecyclerView rv_third;

        public MyViewHolder(View itemView) {
            super(itemView);
            type_gc_name = (TextView) itemView.findViewById(R.id.type_second_gcname);
            rv_third = (RecyclerView) itemView.findViewById(R.id.recyclerViewThird);
        }
    }
}
