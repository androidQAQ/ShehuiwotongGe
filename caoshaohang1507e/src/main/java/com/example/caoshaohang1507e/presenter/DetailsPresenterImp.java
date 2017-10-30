package com.example.caoshaohang1507e.presenter;

import android.content.Context;

import com.example.caoshaohang1507e.bean.DetailsBean;
import com.example.caoshaohang1507e.model.DetailsModel;
import com.example.caoshaohang1507e.model.DetailsModelImp;
import com.example.caoshaohang1507e.view.DetailsView;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public class DetailsPresenterImp implements DetailsPresenter ,DetailsShow{
    private DetailsView detailsView;

    private final DetailsModel detailsModel;

    public DetailsPresenterImp(DetailsView detailsView) {

        this.detailsView = detailsView;

        detailsModel = new DetailsModelImp();
    }
    //给m层传递数据
    @Override
    public void pass(Context context) {
        //关联p层m层
        detailsModel.getData(context,this);
    }

    //m层请求到数据后  传递给v层
    @Override

    public void onData(DetailsBean bean) {

        if(detailsView != null){

            detailsView.setData(bean);
        }

    }
}
