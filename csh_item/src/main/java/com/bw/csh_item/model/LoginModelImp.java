package com.bw.csh_item.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.LogBean;
import com.bw.csh_item.presenter.LoginFinish;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by 曹少航 on 2017/10/17.
 */

public class LoginModelImp implements LoginModel {
    @Override
    public void login(Context context, String loginname, String loginPwd, LoginFinish finish) {
        if(TextUtils.isEmpty(loginname) || TextUtils.isEmpty(loginPwd)){
            finish.onNameError();
            finish.onPwdError();
        }else{
            loginData(context, loginname, loginPwd, finish);
        }

    }

    @Override
    public void loginData(final Context context, String loginname, String loginpwd, final LoginFinish finish) {
        Map<String,String> map = new HashMap<>();
        map.put("username",loginname);
        map.put("password",loginpwd);
        map.put("client", MyApi.CLIENT);
        OkHttp3Utils.doPost(MyApi.LOG_PATH, map, new GsonObjectCallback<LogBean>() {
            @Override
            public void onUi(LogBean logBean) {
                if(logBean.getCode()==200){
                    finish.onLoginSuccess(logBean);
                    Toast.makeText(context, "登录成功0000", Toast.LENGTH_SHORT).show();
                    //保存登录信息
                    MyApi.editor.putString("key",logBean.getDatas().getKey()).commit();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
}
