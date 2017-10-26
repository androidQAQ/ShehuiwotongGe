package com.bw.csh_item.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bw.csh_item.R;
import com.bw.csh_item.Utiles.MyDecoration;
import com.bw.csh_item.adapter.MyAdapter_SecondType;
import com.bw.csh_item.adapter.MyAdapter_Type;
import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.TypeBean;
import com.bw.csh_item.bean.TypeSecondBean;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;


import java.io.IOException;
import java.util.List;


import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/11.
 */

public class Fragment2 extends Fragment {
    private RecyclerView recycler_left,recycler_right;
    private View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment2,null);
        //获取资源ID
        initView();

        return v;
    }
    //初始化控件
    private void initView() {
        recycler_left = (RecyclerView) v.findViewById(R.id.recycler_left);
        recycler_right = (RecyclerView) v.findViewById(R.id.recycler_right);
        //获取信息
       getData();
    }

    /**
     * 获取列表信息
     */
    private void getData() {
        OkHttp3Utils.doGet(MyApi.FirstList, new GsonObjectCallback<TypeBean>() {
            @Override
            public void onUi(final TypeBean typeBean) {
                List<TypeBean.DatasBean.ClassListBean> class_list = typeBean.getDatas().getClass_list();
                recycler_left.setLayoutManager(new LinearLayoutManager(getActivity()));
                //设置下划线
                recycler_left.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
                final MyAdapter_Type adapter_type = new MyAdapter_Type(class_list,getActivity());
                recycler_left.setAdapter(adapter_type);

                adapter_type.setOnRecyclerViewItemClickListener(new MyAdapter_Type.onRecyclerViewItemClickListener() {
                    @Override
                    public void recyclerViewListener(int position) {
                        adapter_type.notifyDataSetChanged();
                        //点击后请求二级数据
                        getSecondData(typeBean.getDatas().getClass_list().get(position).getGc_id());
                    }
                });
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
    /**
     * 请求二级数据
     */
    private void getSecondData(String gc_id) {
        OkHttp3Utils.doGet(MyApi.FirstList + "&gc_id=" + gc_id, new GsonObjectCallback<TypeSecondBean>() {
            @Override
            public void onUi(TypeSecondBean typeSecondBean) {
                //二级列表的数据 添加到适配器
                List<TypeSecondBean.DatasBean.ClassListBean> class_list = typeSecondBean.getDatas().getClass_list();
                recycler_right.setLayoutManager(new LinearLayoutManager(getActivity()));

                MyAdapter_SecondType myAdapter_secondType = new MyAdapter_SecondType(class_list,getActivity());
                recycler_right.setAdapter(myAdapter_secondType);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

}
