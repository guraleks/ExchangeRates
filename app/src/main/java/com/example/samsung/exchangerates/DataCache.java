package com.example.samsung.exchangerates;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Samsung on 24.10.2017.
 */

public class DataCache {

    public Map map;
    public List<String> listOfCurrencies = new ArrayList();
    public String choice;
    public List<Data> dataList = new ArrayList();

    private static volatile DataCache s;

    private DataCache(){}

    public static DataCache getInstance(){
        if (s != null ) return s;
        synchronized(DataCache.class){
            if (s == null ) {
                s = new DataCache();
            }
        }
        return s;
    }
}
