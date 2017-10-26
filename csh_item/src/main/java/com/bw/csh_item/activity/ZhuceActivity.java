package com.bw.csh_item.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.csh_item.R;
import com.bw.csh_item.presenter.RegisterPreImp;
import com.bw.csh_item.presenter.RegisterPresenter;
import com.bw.csh_item.view.RegisterView;


/**
 * Created by 曹少航 on 2017/10/12.
 */
//{"code":200,"datas":{"username":"13621059509","userid":"13","key":"b647c57595586a27e3f61712562ee6e8"}}注册成功返回
public class ZhuceActivity extends BaseActivity implements RegisterView {
    //注册按钮
    private Button bt_zhuce;
    private EditText name1,pwd1,pwd2 ,et_email;
    private RegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce2);
        name1= (EditText) findViewById(R.id.et_name);
        pwd1= (EditText) findViewById(R.id.et_pwd1);
        pwd2= (EditText) findViewById(R.id.et_pwd2);
        et_email= (EditText) findViewById(R.id.et_email);
        bt_zhuce= (Button) findViewById(R.id.bt_zhuce);
        presenter=new RegisterPreImp(this);
        //注册监听
        bt_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.validatapess(ZhuceActivity.this,name1.getText().toString(),pwd1.getText().toString(),pwd2.getText().toString(),et_email.getText().toString());
            }
        });


    }
    //按返回按钮返回到登录界面
    public void btnBack2(View v){
        startActivity(new Intent(this,LoginActivity.class));
    }


    @Override
    public void setNameError() {
        name1.setError("name can no Empty");
    }

    @Override
    public void setPwdError() {
        pwd1.setError("pwd can no Empty");
    }

    @Override
    public void setPwdTwoError() {
        pwd2.setError("pwd2 can no Empty");
    }

    @Override
    public void setEmailError() {
        et_email.setError("email can not Empty");
    }

    @Override
    public void toLoginActivity() {
        startActivity(new Intent(ZhuceActivity.this,LoginActivity.class));

    }
}
