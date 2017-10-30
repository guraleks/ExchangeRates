package com.example.samsung.exchangerates.FirstFragmentPackage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.samsung.exchangerates.ChoiceFragmentPackage.ChoiceFragment;
import com.example.samsung.exchangerates.DataCache;
import com.example.samsung.exchangerates.MainActivity;
import com.example.samsung.exchangerates.R;
import com.example.samsung.exchangerates.RVAdapter;

import java.util.List;

/**
 * Created by Samsung on 24.10.2017.
 */

public class FirstFragment extends Fragment implements View{

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Presenter presenter;

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View v = inflater.inflate(R.layout.first_fragment, container, false);
        setHasOptionsMenu(true);
        rv = v.findViewById(R.id.rv);
        presenter = new Presenter(this);

        renderData(presenter.getDataList());

        return v;
    }

    @Override
    public void renderData(List dataList) {
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(layoutManager);
        adapter = new RVAdapter(dataList, getFragmentManager().findFragmentById(R.id.fragmentContainer));
        rv.setAdapter(adapter);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Fragment fragment = new ChoiceFragment();
            fragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}