package com.bw.csh_item.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.bw.csh_item.Fragment.Fragment1;
import com.bw.csh_item.Fragment.Fragment2;
import com.bw.csh_item.Fragment.Fragment3;
import com.bw.csh_item.Fragment.Fragment5;
import com.bw.csh_item.R;
import com.bw.csh_item.api.MyApi;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomTabBar bottomTabBar;
        //获取保存的登录状态
        MyApi.init(getApplicationContext());

            bottomTabBar= (BottomTabBar) findViewById(R.id.bottomBar);
            bottomTabBar.init(getSupportFragmentManager())
                    .setImgSize(90, 90)
                    .setFontSize(12)
                    .setTabPadding(4, 6, 10)
                    .setChangeColor(Color.GREEN, Color.RED)
                    .addTabItem(" ",R.mipmap.uik_nav_home_selected, R.mipmap.uik_nav_home_normal,  Fragment1.class)
                    .addTabItem("  ", R.mipmap.ic_nav_message_selected, R.mipmap.ic_nav_message_normal, Fragment2.class)
                    .addTabItem("   ",R.mipmap.uik_nav_cart_selected,  R.mipmap.uik_nav_cart_normal, Fragment3.class)
                    .addTabItem("    ",  R.mipmap.sec,R.mipmap.uik_nav_my_normal, Fragment5.class) ;
    }
}
