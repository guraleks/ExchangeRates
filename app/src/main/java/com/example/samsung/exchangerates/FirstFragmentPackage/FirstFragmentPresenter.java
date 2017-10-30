package com.example.samsung.exchangerates.FirstFragmentPackage;

import android.database.Cursor;
import android.os.Handler;

import com.example.samsung.exchangerates.Data;
import com.example.samsung.exchangerates.DataBaseCache;
import com.example.samsung.exchangerates.DataCache;
import com.example.samsung.exchangerates.DatabaseHelper;

import java.util.List;

import static com.example.samsung.exchangerates.DatabaseHelper.TABLE;

/**
 * Created by Samsung on 25.10.2017.
 */

public class FirstFragmentPresenter {

    private View view;
    private Handler handler;

    public FirstFragmentPresenter(View view) {
        this.view = view;
    }

    private List<Data> getDataList() {
        return DataCache.getInstance().dataList;
    }


    public void initHandler() {
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == 0) {
                    setData();
                }
            }
        };
    }

    private void setData() {
        view.renderData(getDataList());
    }


    public void initDataFromDb() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //получаем данные из бд в виде курсора
                String[] columns = {
                        DatabaseHelper.COLUMN_CURRENCY,
                        DatabaseHelper.COLUMN_RATE
                };
                Cursor userCursor = getDataBaseCacheInstance().db.query(TABLE, columns, null, null, null, null, null);
                // определяем, какие столбцы из курсора будут выводиться в ListView
                if (DataCache.getInstance().dataList.isEmpty()) {
                    while (userCursor.moveToNext()) {
                        String currency = userCursor.getString(0);
                        Double rate = userCursor.getDouble(1);
                        DataCache.getInstance().dataList.add(new Data(currency, rate));
                    }
                }
                userCursor.close();

                handler.sendEmptyMessage(0);
            }
        });

        t.start();
    }


    private DataBaseCache getDataBaseCacheInstance() {
        return DataBaseCache.getInstance();
    }

}
