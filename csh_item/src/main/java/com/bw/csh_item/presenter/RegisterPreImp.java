package com.bw.csh_item.presenter;

import android.content.Context;

import com.bw.csh_item.model.RegisterModel;
import com.bw.csh_item.model.RegisterModelImp;
import com.bw.csh_item.view.RegisterView;


/**
 * Created by 曹少航 on 2017/10/13.
 */

public class RegisterPreImp implements RegisterPresenter,RegisterFinish{
    RegisterView registerView;
    RegisterModel registerModel;

    public RegisterPreImp(RegisterView registerView) {
        this.registerView = registerView;
        registerModel=new RegisterModelImp();
    }

    @Override
    public void validatapess(Context context, String name, String pwd,String pwd2,String email) {
        registerModel.register(context,name,pwd,pwd2,email,this);
    }

    @Override
    public void onDestory() {
            registerView=null;
    }

    @Override
    public void onNameError() {
        if(registerView!=null){
            registerView.setNameError();

        }
    }

    @Override
    public void onPwdError() {
        if(registerView!=null){
            registerView.setPwdError();
        }
    }

    @Override
    public void onSuccess() {
        if(registerView!=null){
            registerView.toLoginActivity();
        }
    }

    @Override
    public void onPwd2Error() {
        if(registerView!=null){
            registerView.setPwdTwoError();
        }
    }

    @Override
    public void onEmailError() {
        registerView.setEmailError();
    }
}
