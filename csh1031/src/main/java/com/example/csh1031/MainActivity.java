package com.example.csh1031;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.csh1031.adapter.MyAdapter;
import com.example.csh1031.bean.MBean;
import com.example.csh1031.presenter.DataPresenter;
import com.example.csh1031.presenter.DataPresenterImpl;
import com.example.csh1031.view.DataView;

import java.util.List;

public class MainActivity extends BaseActivity<DataPresenter> implements DataView {

    private RecyclerView recycler;
    private DataPresenter presenter;
    //将数据源给adapter
    @Override
    public void setData(MBean bean) {
        List<MBean.DataBean> data = bean.getData();
        MyAdapter adapter = new MyAdapter(this,data);
        recycler.setAdapter(adapter);
    }

    //加载视图
    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    //找控件
    @Override
    protected void initView() {
        recycler= (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    //P层管理view和presenter
    @Override
    protected DataPresenter getPresenter() {
        //p关联v
        presenter=new DataPresenterImpl(this);
        //p 关联 m 默认请求一次数据
        presenter.relate();
        return presenter;
    }
}
