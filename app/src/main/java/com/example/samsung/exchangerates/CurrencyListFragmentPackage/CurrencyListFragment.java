package com.example.samsung.exchangerates.CurrencyListFragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.samsung.exchangerates.DataCache;
import com.example.samsung.exchangerates.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samsung on 24.10.2017.
 */

public class CurrencyListFragment extends Fragment implements com.example.samsung.exchangerates.CurrencyListFragmentPackage.View {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private CurrencyListFragmentPresenter presenter = new CurrencyListFragmentPresenter(this);
    private EditText editText;

    private List<String> listOfCurrencies = new ArrayList<>();



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.currency_list, container, false);
        listView = v.findViewById(R.id.listView);
        editText = v.findViewById(R.id.editText);

        presenter.getData();

        listOfCurrencies.addAll(DataCache.getInstance().listOfCurrencies);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                DataCache.getInstance().choice = (String) listView.getItemAtPosition(position);
                fragmentManager.popBackStack();
            }
        });

        textChangedListener();

        return v;
    }

    private void textChangedListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    initList();
                } else {
                    searchItem(editable.toString());
                }
            }
        });
    }


    public void searchItem(String textToSearch) {
        DataCache.getInstance().listOfCurrencies.clear();
        for (String s : listOfCurrencies) {
            if (s.toLowerCase().contains(textToSearch.toLowerCase())) {
                DataCache.getInstance().listOfCurrencies.add(s);
            }
        }
        adapter.notifyDataSetChanged();
    }


    public void initList() {
        DataCache.getInstance().listOfCurrencies.clear();
        DataCache.getInstance().listOfCurrencies.addAll(listOfCurrencies);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void renderData(List currencies) {
        adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                R.layout.currency_list_item, currencies);
        listView.setAdapter(adapter);
        if (currencies.isEmpty()) {
            Toast.makeText(getContext(), "PLEASE WAIT...", Toast.LENGTH_LONG).show();
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    public void setAdapter() {
        DataCache.getInstance().listOfCurrencies.addAll(presenter.getListOfCurrencies());
        adapter.notifyDataSetChanged();
    }
}
