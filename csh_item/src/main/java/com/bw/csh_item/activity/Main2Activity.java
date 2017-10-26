package com.bw.csh_item.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bw.csh_item.R;


public class Main2Activity extends AppCompatActivity {

    private ImageView image_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        image_start = (ImageView) findViewById(R.id.Start_image);
        //淡入淡出效果
        AlphaAnimation animation = new AlphaAnimation(1,0);
        //设置动画时间
        animation.setDuration(3000);
        image_start.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画完成后进行跳转
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
