package com.example.mvpdemo.presenter;

import android.content.Context;

import com.example.mvpdemo.DetailsBean;
import com.example.mvpdemo.model.DetailsModel;
import com.example.mvpdemo.model.DetailsModelImpl;
import com.example.mvpdemo.view.DetailsView;

/**
 * Created by 曹少航 on 2017/10/26.
 */

public class DetailsPresenterImpl implements DetailsPresenter,DetailsShow {

    private DetailsView detailsView;
    private final DetailsModel detailsModel;

    public DetailsPresenterImpl(DetailsView detailsView) {
        this.detailsView = detailsView;
        detailsModel = new DetailsModelImpl();
    }

    //给m层传递数据
    @Override
    public void pass(Context context, int id) {
        //关联p层m层
        detailsModel.getData(context,id,this);
    }

    //m层请求到数据后  传递给v层
    @Override
    public void onData(DetailsBean bean) {
        if(detailsView != null){
            detailsView.setData(bean);
        }
    }
}
