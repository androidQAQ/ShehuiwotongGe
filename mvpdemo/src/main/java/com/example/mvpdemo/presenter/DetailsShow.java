package com.example.mvpdemo.presenter;

import com.example.mvpdemo.DetailsBean;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public interface DetailsShow {
    /**
     * 设置一个中间值  进行p层的数据传输
     * @param bean
     */
    void onData(DetailsBean bean);
}
