package com.example.csh1031.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.example.csh1031.OnFinishLisenter;
import com.example.csh1031.bean.MBean;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/31.
 */

public class DataModelImpl implements DataModel {
    public  static  final String url="http://ic.snssdk.com/2/article/v25/stream/?category=news_entertainment&count=20&min_behot_time=1455522338&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000";
    @Override
    public void getData(final OnFinishLisenter lisenter) {
        OkHttp3Utils.doGet(url, new GsonObjectCallback<MBean>() {
            @Override
            public void onUi(MBean mBean) {
                Log.e("SSSS",mBean.toString());
                if(lisenter!=null){
                    lisenter.OnSuccess(mBean);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }
}
