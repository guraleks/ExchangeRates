package com.example.samsung.exchangerates.FirstFragmentPackage;

import com.example.samsung.exchangerates.DataCache;

import java.util.List;

/**
 * Created by Samsung on 25.10.2017.
 */

public class Presenter {
    View view;

    public Presenter(View view) {
        this.view = view;
    }

    public List getDataList() {
        return DataCache.getInstance().dataList;
    }

    public void getData() {
        view.renderData(getDataList());
    }
}
