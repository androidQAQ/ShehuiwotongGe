package com.example.bottomnabar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 曹少航 on 2017/10/24.
 */

public class Fragment1 extends Fragment {

    private RecyclerView recycler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment01,null);
        recycler=v.findViewById(R.id.recycler);
        //设置布局
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyRecyclerView adapter= new MyRecyclerView(getActivity());
        recycler.setAdapter(adapter);

        return v;
    }
}
