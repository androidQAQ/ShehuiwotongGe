package com.example.day1.presenter;

import com.example.day1.OnFinishListener;
import com.example.day1.bean.Bean;
import com.example.day1.model.DataModel;
import com.example.day1.model.DataModelImpl;
import com.example.day1.view.DataView;

/**
 * Created by 曹少航 on 2017/10/31.
 */

public class DataPresenterImpl implements DataPresenter,OnFinishListener {
    DataView dataView;
    private final DataModel dataModel;

    public DataPresenterImpl(DataView dataView) {
        this.dataView = dataView;
        dataModel =
                new DataModelImpl();
    }

    @Override
    public void Relate() {
        dataModel.getData(this);
    }

    @Override
    public void Success(Bean bean) {
        dataView.setData(bean);
    }
}
