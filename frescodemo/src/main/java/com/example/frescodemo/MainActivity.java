package com.example.frescodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
         SimpleDraweeView sdv= (SimpleDraweeView) findViewById(R.id.simp);
        //拿到图片地址
        Uri uri=Uri.parse("http://dynamic-image.yesky.com/740x-/uploadImages/2015/163/50/690V3VHW0P77.jpg");
        sdv.setImageURI(uri);
    }
}
