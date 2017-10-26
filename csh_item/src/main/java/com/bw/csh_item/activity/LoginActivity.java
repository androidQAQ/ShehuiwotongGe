package com.bw.csh_item.activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import com.bw.csh_item.R;

/**
 * Created by 曹少航 on 2017/10/12.
 */

public class LoginActivity extends BaseActivity {
    private Button my_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        Login2Activity.activity = this;
        my_register= (Button) findViewById(R.id.my_register);
       //点击进入注册界面
        my_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ZhuceActivity.class));
            }
        });
    }

    //进入登录界面
    public void btnLogin(View v){
        startActivity(new Intent(this,Login2Activity.class));
    }

}
