package com.example.caoshaohang1507e.model;

import android.content.Context;

import com.example.caoshaohang1507e.presenter.DetailsShow;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public interface DetailsModel {
        /**
         * 请求数据的方法
         */
        void getData(Context context, DetailsShow show);

}
