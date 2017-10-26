package com.bw.csh_item.view;

/**
 * Created by 曹少航 on 2017/10/13.
 */

public interface RegisterView {
    //用户名输入错误
    void setNameError();
    //密码输入错误
    void setPwdError();
    //确认密码
    void setPwdTwoError();
    //确认邮箱
    void setEmailError();
    //登录跳转
    void toLoginActivity();

}
