package com.bw.csh_item.adapter;

import android.content.Context;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;



/**
 * Created by 曹少航 on 2017/10/18.
 */

public class MyApplication extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }


}
