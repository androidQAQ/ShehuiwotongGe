package com.example.csh_demo1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar bottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomTabBar= (BottomTabBar) findViewById(R.id.bottomBar);
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(40, 40)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.BLACK)
                .addTabItem("第一页", R.mipmap.ic_nav_cart_press,R.mipmap.ic_nav_cart,  Fragment1.class)
                .addTabItem("第二页",  R.mipmap.ic_nav_home_press,R.mipmap.ic_nav_home, Fragment2.class)
                .addTabItem("第三页",  R.mipmap.ic_nav_user_press,R.mipmap.ic_nav_user, Fragment3.class)
                .addTabItem("第四页", R.mipmap.ic_nav_class_press, R.mipmap.ic_nav_class, Fragment4.class) ;

    }


}
