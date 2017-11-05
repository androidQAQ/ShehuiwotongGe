package com.example.csh_demo1.presenter;

import com.example.csh_demo1.OnFinishListener;
import com.example.csh_demo1.bean.MBean;
import com.example.csh_demo1.model.DataModel;
import com.example.csh_demo1.model.DataModelImpl;
import com.example.csh_demo1.view.DataView;

import java.util.List;

/**
 * Created by 曹少航 on 2017/11/4.
 */

public class DataPresenterImpl implements DataPresenter,OnFinishListener {
    DataView dataview;
    private final DataModel model;

    public DataPresenterImpl(DataView dataview) {
        this.dataview = dataview;
        model = new DataModelImpl();

    }

    @Override
    public void relate() {
       model.getData(this);

    }

    @Override
    public void Onsuccess(List<MBean> list) {
        dataview.setData(list);
    }
}
