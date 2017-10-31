package com.example.csh1031;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 曹少航 on 2017/10/31.
 */

public abstract class BaseActivity<T> extends AppCompatActivity {
    public  T t;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(initLayout());
        //创建p
        t=getPresenter();
        //找控件
        initView();

    }


    //加载布局抽象类
    public  abstract  int initLayout();
    //找控件抽象方法
    protected  abstract void initView();
    //创建P
    protected abstract T getPresenter();


}
