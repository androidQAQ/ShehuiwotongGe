package com.example.mvpdemo.model;

import android.content.Context;

import com.example.mvpdemo.presenter.DetailsShow;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public interface DetailsModel {
    /**
     * 请求数据的方法
     */
    void getData(Context context, int id, DetailsShow show);
}
