package com.example.caoshaohang1507e.model;

import android.content.Context;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.example.caoshaohang1507e.Api.MyApi;
import com.example.caoshaohang1507e.bean.DetailsBean;
import com.example.caoshaohang1507e.presenter.DetailsShow;
import java.io.IOException;
import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public class DetailsModelImp implements DetailsModel{
    @Override
    public void getData(Context context,final DetailsShow show) {
        OkHttp3Utils.doGet(MyApi.DetailsURL, new GsonObjectCallback<DetailsBean>() {
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
