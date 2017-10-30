package com.example.samsung.exchangerates;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Samsung on 30.10.2017.
 */

public class DataBaseCache {

    public DatabaseHelper databaseHelper;
    public SQLiteDatabase db;

    private static volatile DataBaseCache s;

    private DataBaseCache(){}

    public static DataBaseCache getInstance(){
        if (s != null ) return s;
        synchronized(DataBaseCache.class){
            if (s == null ) {
                s = new DataBaseCache();
            }
        }
        return s;
    }
}
