package com.bw.csh_item.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bw.csh_item.R;
import com.bw.csh_item.activity.SouActivity;

import com.bw.csh_item.adapter.MyMainRecyclerAdapter;
import com.youth.banner.Banner;

/**
 * Created by 曹少航 on 2017/10/11.
 */
public class Fragment1 extends Fragment {
    private RecyclerView recyclerView;

    private RelativeLayout search;
    private Banner banner;
    private ImageView sao;//扫一扫

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null);
        //找控件
        sao = (ImageView) view.findViewById(R.id.sao);
        //多条目加载布局
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyMainRecyclerAdapter adapter = new MyMainRecyclerAdapter(getActivity());

        recyclerView.setAdapter(adapter);


        //点击控件进行搜索
        search = (RelativeLayout) view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getActivity(), SouActivity.class));


            }
        });

        //点击扫一扫跳转到扫一扫界面
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        return view;
    }

}
