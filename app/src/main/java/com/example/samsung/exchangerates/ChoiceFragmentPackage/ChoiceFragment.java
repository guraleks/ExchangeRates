package com.example.samsung.exchangerates.ChoiceFragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.samsung.exchangerates.CurrencyListFragmentPackage.CurrencyListFragment;
import com.example.samsung.exchangerates.DataBaseCache;
import com.example.samsung.exchangerates.DataCache;
import com.example.samsung.exchangerates.R;

/**
 * Created by Samsung on 24.10.2017.
 */

public class ChoiceFragment extends Fragment {

    private Button chooseButton;
    private Button saveButton;
    private ChoiceFragmentPresenter presenter = new ChoiceFragmentPresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.choice_fragment, container, false);
        chooseButton = v.findViewById(R.id.chooseButton);
        saveButton = v.findViewById(R.id.saveButton);

        if (DataCache.getInstance().choice != null) {
            chooseButton.setText(DataCache.getInstance().choice);
        }

        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Действия при нажатии на кнопку "CHOOSE"
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Fragment fragment = new CurrencyListFragment();
                fragmentManager
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainer, fragment)
                        .commit();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Действия при нажатии на кнопку "SAVE"
                presenter.addToDataList(DataCache.getInstance().choice);
                presenter.addToDb(DataCache.getInstance().choice, DataBaseCache.getInstance().db);
                DataCache.getInstance().choice = null;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return v;
    }

}
