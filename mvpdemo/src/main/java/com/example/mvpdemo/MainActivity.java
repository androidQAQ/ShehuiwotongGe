package com.example.mvpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mvpdemo.presenter.DetailsPresenter;
import com.example.mvpdemo.presenter.DetailsPresenterImpl;
import com.example.mvpdemo.view.DetailsView;

public class MainActivity extends AppCompatActivity implements DetailsView {


    private RecyclerView rv_details;
    private int id=11;
    private DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建presenter实例
        presenter = new DetailsPresenterImpl(this);
        //接收id

        initView();

    }

    private void initView() {
        rv_details = (RecyclerView) findViewById(R.id.rv_details);
        presenter.pass(MainActivity.this,id);
    }

    /**
     * 设置数据
     */
    @Override
    public void setData(DetailsBean bean) {
        rv_details.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        MyDetailsAdapter detailsAdapter = new MyDetailsAdapter(MainActivity.this,bean.getStories());
        rv_details.setAdapter(detailsAdapter);
    }
}
