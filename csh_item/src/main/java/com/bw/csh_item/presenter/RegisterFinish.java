package com.bw.csh_item.presenter;

/**
 * Created by 曹少航 on 2017/10/13.
 */

public interface RegisterFinish {
    void onNameError();
    void onPwdError();
    void onSuccess();
    void onPwd2Error();
    void onEmailError();


}
