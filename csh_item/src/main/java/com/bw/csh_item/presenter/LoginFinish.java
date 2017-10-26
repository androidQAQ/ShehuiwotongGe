package com.bw.csh_item.presenter;


import com.bw.csh_item.bean.LogBean;

/**
 * Created by 曹少航 on 2017/10/17.
 */

public interface LoginFinish {
    /**
     * 用户名错误
     */
    void onNameError();

    /**
     * 密码错误
     */
    void onPwdError();

    /**
     * 登录成功
     */
    void onLoginSuccess(LogBean logBean);

    /**
     * 登录失败
     */
    void onLoginFail();

}
