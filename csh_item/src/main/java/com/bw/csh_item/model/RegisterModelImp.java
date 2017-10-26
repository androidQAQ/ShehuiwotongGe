package com.bw.csh_item.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.RegBean;
import com.bw.csh_item.presenter.RegisterFinish;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/13.
 */

public class RegisterModelImp implements RegisterModel {

    @Override
    public void register(final Context context, String name, String pwd, String pwd2, String email, final RegisterFinish finish) {
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd2) || TextUtils.isEmpty(email)){
           finish.onPwdError();
            finish.onNameError();
            finish.onPwd2Error();
            finish.onEmailError();

            return;
        }else{
            Map<String,String> map = new HashMap<>();
            map.put("username",name);
            map.put("password",pwd);
            map.put("password_confirm",pwd2);
            map.put("email",email);
            map.put("client", MyApi.CLIENT);
            OkHttp3Utils.doPost(MyApi.REG_PATH, map, new GsonObjectCallback<RegBean>() {
                @Override
                public void onUi(RegBean regBean) {
                    if(regBean.getCode()==200){
                        finish.onSuccess();
                        Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });


        }
    }
}
