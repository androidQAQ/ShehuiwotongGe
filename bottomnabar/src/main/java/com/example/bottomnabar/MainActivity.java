package com.example.bottomnabar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt= (BottomTabBar) findViewById(R.id.btn);
        bt.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.GREEN, Color.RED)
                .addTabItem("第一项", R.mipmap.ic_launcher, Fragment1.class)
                .addTabItem("第二项", R.mipmap.ic_launcher, Fragment2.class)
                .addTabItem("第三项", R.mipmap.ic_launcher, Fragment3.class)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Toast.makeText(MainActivity.this, "这是"+name, Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
