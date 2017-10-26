package com.bawei.zonghe.model;

import android.content.Context;

import com.bawei.zonghe.presenter.DetailsShow;



public interface DetailsModel {

    /**
     * 请求数据的方法
     */
    void getData(Context context,int id, DetailsShow show);

}
