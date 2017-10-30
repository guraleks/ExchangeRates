package com.example.samsung.exchangerates;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.samsung.exchangerates.FirstFragmentPackage.FirstFragment;

import static com.example.samsung.exchangerates.DatabaseHelper.TABLE;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    public SQLiteDatabase db;
    private Cursor userCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataCache.getInstance();
        new ParseTask(this).execute();

        initDataFromDb();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FirstFragment();
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }


    private void initDataFromDb() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        // открываем подключение
        db = databaseHelper.getReadableDatabase();
        //получаем данные из бд в виде курсора
        String[] columns = {
                DatabaseHelper.COLUMN_CURRENCY,
                DatabaseHelper.COLUMN_RATE
        };
        userCursor = db.query(TABLE, columns, null, null, null, null, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        if (DataCache.getInstance().dataList.isEmpty()) {
            while (userCursor.moveToNext()) {
                String currency = userCursor.getString(0);
                Double rate = userCursor.getDouble(1);
                DataCache.getInstance().dataList.add(new Data(currency, rate));
            }
        }
    }


    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (f instanceof FirstFragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }


    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
    }
}
