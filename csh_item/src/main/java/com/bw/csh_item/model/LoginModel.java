package com.bw.csh_item.model;

import android.content.Context;

import com.bw.csh_item.presenter.LoginFinish;


/**
 * Created by 曹少航 on 2017/10/17.
 */

public interface LoginModel {
    void login(Context context, String loginname, String loginPwd, LoginFinish finish);
    void loginData(Context context, String loginname, String loginpwd, LoginFinish finish);
}
