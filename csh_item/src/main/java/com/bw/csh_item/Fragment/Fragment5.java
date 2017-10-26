package com.bw.csh_item.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.csh_item.R;
import com.bw.csh_item.activity.LoginActivity;
import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.LogBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by 曹少航 on 2017/10/11.
 */

public class Fragment5 extends Fragment {
    private ImageView login_tou;
    private TextView tv_news;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment5,null);
        login_tou=(ImageView)v.findViewById(R.id.login_tou);
        tv_news= (TextView) v.findViewById(R.id.login_state);


        //获取一个值 默认为false 直接跳转至登陆界面
        boolean isLogin = MyApi.sharedPreferences.getBoolean("isLogin", false);

        if(isLogin == false){
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }
        //注册EventBus
        EventBus.getDefault().register(this);
        //点击头像跳转到登录界面
        login_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return v;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEvent(LogBean logBean){
        tv_news.setText(logBean.getDatas().getUsername());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
