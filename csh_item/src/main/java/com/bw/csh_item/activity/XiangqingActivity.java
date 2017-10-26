package com.bw.csh_item.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bw.csh_item.R;
import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.UnregBean;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 曹少航 on 2017/10/18.
 */

public class XiangqingActivity extends BaseActivity {
    private TextView tv_join;
    private TextView tv_buy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        tv_buy= (TextView) findViewById(R.id.buy);
        tv_join= (TextView) findViewById(R.id.join);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");
        String price = intent.getStringExtra("price");
        final String good_id=intent.getStringExtra("good_id");//得到商品ID

        TextView tv1= (TextView) findViewById(R.id.tv1_data);
        TextView tv2 = (TextView) findViewById(R.id.tv1_price);
        ImageView imgg = (ImageView) findViewById(R.id.img_data);

        tv1.setText(name);
        tv2.setText(price);
        Picasso.with(XiangqingActivity.this).load(img).into(imgg);

        //跳转到购物车
        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存值
                Map <String,String> map=new HashMap<String, String>();
                map.put("key", MyApi.sharedPreferences.getString("key",""));
                map.put("goods_id",good_id);
                map.put("quantity","1");
                OkHttp3Utils.doPost(MyApi.ShopList, map, new GsonObjectCallback<UnregBean>() {
                    @Override
                    public void onUi(UnregBean unregBean) {
                        if(unregBean.getCode() == 200){
                            Toast.makeText(XiangqingActivity.this,"成功添加到购物车",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });

            }
        });
        //跳转到支付


    }
}
