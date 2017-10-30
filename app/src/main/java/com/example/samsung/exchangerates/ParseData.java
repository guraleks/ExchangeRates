package com.example.samsung.exchangerates;

import java.util.Map;

/**
 * Created by Samsung on 24.10.2017.
 */

public class ParseData {

    private String base;
    private String date;
    private Map<String, Double> rates;

    public ParseData() {}

    public Map<String, Double> getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }
}
