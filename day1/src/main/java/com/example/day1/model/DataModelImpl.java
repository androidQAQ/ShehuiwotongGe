package com.example.day1.model;

import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.example.day1.MyApi.Api;
import com.example.day1.OnFinishListener;
import com.example.day1.bean.Bean;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/30.
 */

public class DataModelImpl implements DataModel {


    @Override
    public void getData(final OnFinishListener listener) {
        OkHttp3Utils.doGet(Api.RefreshURL, new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                if(listener!=null){
                    listener.Success(bean);
                }
            }
            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
