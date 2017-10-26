package com.example.csh_demo2;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView Xrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        Xrecycler= (XRecyclerView) findViewById(R.id.Xrcycler);
        Xrecycler.setLayoutManager(new LinearLayoutManager(this));
        Xrecycler.addItemDecoration(new MyDecoration(this,MyDecoration.VERTICAL_LIST));

        Xrecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //请求数据
                getData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Xrecycler.refreshComplete();
                    }
                },2000);
            }
            @Override
            public void onLoadMore() {

            }
        });
      //请求数据
        getData();
    }

    private void getData() {
        //设置适配器
        String url="http://news-at.zhihu.com/api/4/news/latest";

        OkHttp3Utils.doGet(url, new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                List<Bean.StoriesBean> stories = bean.getStories();
                //子条目点击
                MyAdapter adapter = new MyAdapter(MainActivity.this,stories);
                Xrecycler.setAdapter(adapter);

                adapter.setOnRecyclerListener(new MyAdapter.OnRecyclerListener() {
                    @Override
                    public void onRecycle(int position) {
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);

                    }
                });
            }
            @Override
            public void onFailed(Call call, IOException e) {
            }
        });


    }


}
