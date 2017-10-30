package com.example.caoshaohang1507e.view;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.caoshaohang1507e.R;
import com.example.caoshaohang1507e.adapter.MyAdapter;
import com.example.caoshaohang1507e.bean.DetailsBean;
import com.example.caoshaohang1507e.presenter.DetailsPresenter;
import com.example.caoshaohang1507e.presenter.DetailsPresenterImp;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements DetailsView{
    private RecyclerView recycler;
    private DetailsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建presenter实例
        presenter = new DetailsPresenterImp(this);
        initView();
    }
    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        presenter.pass(MainActivity.this);
    }
    /**
     * 设置数据
     */
    @Override
    public void setData(DetailsBean bean) {
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        List<DetailsBean.SongListBean> song_list = bean.getSong_list();
         MyAdapter detailsAdapter = new MyAdapter(MainActivity.this,song_list);
        recycler.setAdapter(detailsAdapter);
        //点击recycleView条目进入多条目
        detailsAdapter.setOnRecyclerListener(new MyAdapter.OnRecyclerListener() {
            @Override
            public void onRecycle(int position) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

    }



}
