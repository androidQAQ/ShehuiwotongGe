package com.bw.csh_item.view;


import com.bw.csh_item.bean.LogBean;

/**
 * Created by 曹少航 on 2017/10/17.
 */

public interface LoginView {
    void setNameError();
    void setPwdError();
    void setLoginSuccess(LogBean logBean);
    void setLoginFail();
}
