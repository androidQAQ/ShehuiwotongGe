package com.bw.csh_item.presenter;

import android.content.Context;



/**
 * Created by 曹少航 on 2017/10/13.
 */

public interface RegisterPresenter {

    void validatapess(Context context, String name, String pwd, String pwd2, String email);
    void onDestory();
}
