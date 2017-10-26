package com.bw.csh_item.model;

import android.content.Context;

import com.bw.csh_item.presenter.RegisterFinish;


/**
 * Created by 曹少航 on 2017/10/13.
 */

public interface RegisterModel {
    void register(Context context, String name, String pwd, String pwd2, String email, RegisterFinish finish);


}
