package com.bw.csh_item.api;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 曹少航 on 2017/10/17.
 */

public class MyApi {
    public static final String uri="169.254.131.247";

    public static final String CLIENT="android";
    public static final String REG_PATH="http://"+uri+"/mobile/index.php?act=login&op=register";
    public static final String LOG_PATH="http://"+uri+"/mobile/index.php?act=login";
    public static final String CATCH_PATH="http://"+uri+"/mobile/index.php?act=goods&op=goods_detail&goods_id=100009";
    public static final String FIRST_TYPE="http://"+uri+"/mobile/index.php?act=goods_class";
    public static final  String FirstList="http://169.254.51.175/mobile/index.php?act=goods_class";
    public static final String SeekList="http://"+uri+"/mobile/index.php?act=goods&op=goods_list&page=100";//搜索列表
    public static final String ShopList="http://"+uri+"/mobile/index.php?act=member_cart&op=cart_add";//购物车
    public static final  String ShopLists="http://"+uri+"/mobile/index.php?act=member_cart&op=cart_list";//购物车列表
    public static final  String DeleteURL="http://"+uri+"/mobile/index.php?act=member_cart&op=cart_del";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

}
