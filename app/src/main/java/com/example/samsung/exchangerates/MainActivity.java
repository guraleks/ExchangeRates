package com.example.samsung.exchangerates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.samsung.exchangerates.FirstFragmentPackage.FirstFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataCache.getInstance();
        DataBaseCache.getInstance();
        new ParseTask(this).execute();

        initDb();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FirstFragment();
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }


    private void initDb() {
        DataBaseCache.getInstance().databaseHelper = new DatabaseHelper(getApplicationContext());
        DataBaseCache.getInstance().db = DataBaseCache.getInstance().databaseHelper.getReadableDatabase();
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
