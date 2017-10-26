package com.bw.csh_item.presenter;

import android.content.Context;

import com.bw.csh_item.bean.LogBean;
import com.bw.csh_item.model.LoginModel;
import com.bw.csh_item.model.LoginModelImp;
import com.bw.csh_item.view.LoginView;


/**
 * Created by 曹少航 on 2017/10/17.
 */

public class LoginPresenterImp implements  LoginPresenter,LoginFinish {
    private LoginView loginView=null;
    private LoginModel loginModel;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImp();
    }

    @Override
    public void loginPass(Context context, String loginname, String loginPwd) {
        loginModel.login(context,loginname,loginPwd,this);
    }

    @Override
    public void onNameError() {
        if(loginView != null){
            loginView.setNameError();
        }
    }

    @Override
    public void onPwdError() {
        if(loginView != null){
            loginView.setPwdError();
        }
    }

    @Override
    public void onLoginSuccess(LogBean logBean) {
        if(loginView != null){
            loginView.setLoginSuccess(logBean);
        }
    }

    @Override
    public void onLoginFail() {
        if(loginView != null){
            loginView.setLoginFail();
        }
    }
}
