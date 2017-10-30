package com.example.samsung.exchangerates.CurrencyListFragmentPackage;

import com.example.samsung.exchangerates.DataCache;

import java.util.List;

/**
 * Created by Samsung on 24.10.2017.
 */

public class Presenter {

    View view;

    public Presenter(View view) {
        this.view = view;
    }

    public void getData() {
        view.renderData(getListOfCurrencies());
    }

    public List getListOfCurrencies() {
        return DataCache.getInstance().listOfCurrencies;
    }
}
