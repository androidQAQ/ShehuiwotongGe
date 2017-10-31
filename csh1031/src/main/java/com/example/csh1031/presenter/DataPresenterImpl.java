package com.example.csh1031.presenter;

import com.example.csh1031.OnFinishLisenter;
import com.example.csh1031.bean.MBean;
import com.example.csh1031.model.DataModel;
import com.example.csh1031.model.DataModelImpl;
import com.example.csh1031.view.DataView;

/**
 * Created by 曹少航 on 2017/10/31.
 */

public class DataPresenterImpl implements  DataPresenter,OnFinishLisenter {
    private DataView dataView;
    private final DataModel dataModel;

    public DataPresenterImpl(DataView dataView) {
        this.dataView = dataView;
        dataModel = new DataModelImpl();
    }

    @Override
    public void relate() {
        //model将数据给OnFinishListener
        dataModel.getData(this);
    }

    @Override
    public void OnSuccess(MBean bean) {
        //view从OnFinishListener拿到数据
        dataView.setData(bean);
    }
}
