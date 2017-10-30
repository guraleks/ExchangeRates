package com.example.samsung.exchangerates.FirstFragmentPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.samsung.exchangerates.Data;
import com.example.samsung.exchangerates.R;
import com.example.samsung.exchangerates.RVAdapter;

import java.util.List;

/**
 * Created by Samsung on 24.10.2017.
 */

public class FirstFragment extends Fragment implements View{

    android.view.View root;

    private RecyclerView rv;
    private RVAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirstFragmentPresenter presenter;

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.first_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = root.findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(layoutManager);
        adapter = new RVAdapter(getFragmentManager().findFragmentById(R.id.fragmentContainer));
        rv.setAdapter(adapter);

        presenter = new FirstFragmentPresenter(this);
        presenter.initHandler();
        presenter.initDataFromDb();
    }

    @Override
    public void renderData(List<Data> dataList) {
        adapter.setData(dataList);
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