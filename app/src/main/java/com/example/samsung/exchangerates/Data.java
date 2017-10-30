package com.example.samsung.exchangerates;

/**
 * Created by Samsung on 25.10.2017.
 */

public class Data {

    private String currency;
    private Double rate;

    public Data(String currency, Double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
