package com.example.frescodemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 曹少航 on 2017/11/1.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //使用他之前初始化
        Fresco.initialize(this);
    }
}
