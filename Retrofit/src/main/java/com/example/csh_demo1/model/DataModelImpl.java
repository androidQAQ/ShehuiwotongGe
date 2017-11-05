package com.example.csh_demo1.model;

import com.example.csh_demo1.OnFinishListener;
import com.example.csh_demo1.api.MyApi;
import com.example.csh_demo1.bean.MBean;
import com.example.csh_demo1.inter.ApiService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 曹少航 on 2017/11/4.
 */

public class DataModelImpl implements DataModel {
    @Override
    public void getData(final OnFinishListener listener) {
    //Retrofit+RxJava
        Retrofit retrofit=new Retrofit.Builder().baseUrl(MyApi.MyApi)
                .addConverterFactory(GsonConverterFactory.create())//支持Gson解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持Rxjava
                .build();
                ApiService service = retrofit.create(ApiService.class);
            Observable<List<MBean>> info = service.getParams("info");
            info.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<MBean>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                        @Override
                        public void onNext(List<MBean> mBeen) {
                            listener.Onsuccess(mBeen);

                        }
                    });

    }
}
