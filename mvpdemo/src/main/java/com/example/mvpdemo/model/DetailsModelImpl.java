package com.example.mvpdemo.model;

import android.content.Context;

import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.example.mvpdemo.DetailsBean;
import com.example.mvpdemo.MyApi;
import com.example.mvpdemo.presenter.DetailsShow;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public class DetailsModelImpl implements DetailsModel {
    @Override
    public void getData(Context context, int id, final DetailsShow show) {
        OkHttp3Utils.doGet(MyApi.DetailsURL+ id, new GsonObjectCallback<DetailsBean>() {
            @Override
            public void onUi(DetailsBean detailsBean) {
                //请求成功后回传给p层
                show.onData(detailsBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
