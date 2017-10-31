package com.example.day1;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.example.day1.MyApi.Api;
import com.example.day1.adapter.MyAdapter;
import com.example.day1.bean.Bean;
import com.example.day1.presenter.DataPresenterImpl;
import com.example.day1.view.DataView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements DataView {

    private RecyclerView rv_new;
    private SwipeRefreshLayout swipeRefresh;
    private MyAdapter adapter;
    private LinearLayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        rv_new = (RecyclerView) findViewById(R.id.rv_new);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        manager = new LinearLayoutManager(this);
        rv_new.setLayoutManager(manager);
        //P层关联view层
        DataPresenterImpl presenter=new DataPresenterImpl(this);
        //调用P层的方法去连接M和V层
        presenter.Relate();

    }
    //从OnFinishListener中获取Bean的值
    @Override
    public void setData(Bean bean) {



        OkHttp3Utils.doGet(Api.RefreshURL, new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                if(adapter==null){
                    //讲数据传入适配器
                    adapter = new MyAdapter(MainActivity.this,bean.getStories(),bean.getTop_stories());
                    rv_new.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
        //下拉刷新
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                OkHttp3Utils.doGet(Api.RefreshURL, new GsonObjectCallback<Bean>() {
                    @Override
                    public void onUi(Bean bean) {
                        List<Bean.StoriesBean> stories=bean.getStories();
                        adapter.refreshMore(stories);
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
                //如果正在刷新  完成后设置为false
                if (swipeRefresh.isRefreshing()){
                    swipeRefresh.setRefreshing(false);
                }
            }
        });
        //给RecycleView添加滚动监听
        rv_new.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastPosition;//设置一个int类型
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(lastPosition+1==adapter.getItemCount()&&newState==RecyclerView.SCROLL_STATE_IDLE){
                    OkHttp3Utils.doGet(Api.RefreshURL, new GsonObjectCallback<Bean>() {
                        @Override
                        public void onUi(Bean bean) {
                            List<Bean.StoriesBean> stories = bean.getStories();
                            adapter.loadMore(stories);
                        }

                        @Override
                        public void onFailed(Call call, IOException e) {

                        }
                    });
                }

            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastPosition=manager.findLastVisibleItemPosition();

            }
        });

    }
}
