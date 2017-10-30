package com.example.samsung.exchangerates;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.example.samsung.exchangerates.CurrencyListFragmentPackage.CurrencyListFragment;
import com.example.samsung.exchangerates.FirstFragmentPackage.FirstFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Samsung on 24.10.2017.
 */

public class ParseTask extends AsyncTask<Void, Void, String> {

    private static final String BASE_URL = "http://api.fixer.io/latest?base=USD";
    private Activity activity;

    public ParseTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        try {
            URL url = new URL(BASE_URL);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ParseData data = gson.fromJson(resultJson, ParseData.class);

        DataCache.getInstance().map = data.getRates();
        DataCache.getInstance().listOfCurrencies.clear();
        DataCache.getInstance().listOfCurrencies.addAll(DataCache.getInstance().map.keySet());

        return resultJson;
    }


    @Override
    protected void onPostExecute(String strJson) {
        Fragment f = ((MainActivity)activity).getCurrentFragment();
        if (f instanceof CurrencyListFragment) {
            ((CurrencyListFragment) f).setAdapter();
        }
        if (f instanceof FirstFragment) {
            ((FirstFragment) f).getAdapter().notifyDataSetChanged();
        }
    }
}