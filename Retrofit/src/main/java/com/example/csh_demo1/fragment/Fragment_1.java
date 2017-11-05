package com.example.csh_demo1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csh_demo1.MyRecyclerAdapter;
import com.example.csh_demo1.R;
import com.example.csh_demo1.bean.MBean;
import com.example.csh_demo1.presenter.DataPresenterImpl;
import com.example.csh_demo1.view.DataView;

import java.util.List;

/**
 * Created by 曹少航 on 2017/10/25.
 */

public class Fragment_1 extends Fragment implements DataView{

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_1,null,false);
        //找控件
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //p层关联View
        DataPresenterImpl  dataPresenter= new DataPresenterImpl(this);
        dataPresenter.relate();

        return view;
    }

    @Override
    public void setData(List<MBean> list) {
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

    }
}
