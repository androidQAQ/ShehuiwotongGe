package com.bw.csh_item.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.csh_item.R;
import com.bw.csh_item.bean.LogBean;
import com.bw.csh_item.presenter.LoginPresenter;
import com.bw.csh_item.presenter.LoginPresenterImp;
import com.bw.csh_item.view.LoginView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 曹少航 on 2017/10/12.
 */

public class Login2Activity extends BaseActivity implements LoginView {
    private Button my_phone_login;
    private EditText et1,et2;
    private LoginPresenter presenter;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denglu);
        my_phone_login= (Button) findViewById(R.id.my_phone_login);
        et1= (EditText) findViewById(R.id.my_phone_user);
        et2= (EditText) findViewById(R.id.my_phone_password);

        presenter=new LoginPresenterImp(this);

        //登录成功返回值显示到Fragment上
        my_phone_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    presenter.loginPass(Login2Activity.this,et1.getText().toString(),et2.getText().toString());
            }
        });
    }


    //返回登录界面
    public void btn_backup(View v){
        startActivity(new Intent(this,LoginActivity.class));
    }

    //重写方法
    @Override
    public void setNameError() {
        et1.setError("name is not null");
    }

    @Override
    public void setPwdError() {
        et2.setError("pwd is not null");
    }

    @Override
    public void setLoginSuccess(LogBean logBean) {
        Toast.makeText(Login2Activity.this, "登录成功", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(logBean);
        Login2Activity.this.finish();
        activity.finish();
    }
    @Override
    public void setLoginFail() {
        Toast.makeText(Login2Activity.this,"登录失败",Toast.LENGTH_SHORT).show();
    }

}
