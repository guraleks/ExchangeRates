package com.example.samsung.exchangerates.ChoiceFragmentPackage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.samsung.exchangerates.Data;
import com.example.samsung.exchangerates.DataCache;

import static com.example.samsung.exchangerates.DatabaseHelper.COLUMN_CURRENCY;
import static com.example.samsung.exchangerates.DatabaseHelper.COLUMN_RATE;
import static com.example.samsung.exchangerates.DatabaseHelper.TABLE;

/**
 * Created by Samsung on 25.10.2017.
 */

public class ChoiceFragmentPresenter {

    public void addToDb(String choice, SQLiteDatabase db) {
        if (choice != null) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CURRENCY, choice);
            values.put(COLUMN_RATE, (Double) DataCache.getInstance().map.get(choice));
            db.insert(TABLE, null, values);
        } else {
        }
    }

    public void addToDataList(String choice) {
        if (choice != null) {
            DataCache.getInstance().dataList.add(new Data(choice, (Double) DataCache.getInstance().map.get(choice)));
        } else {
        }
    }

}
