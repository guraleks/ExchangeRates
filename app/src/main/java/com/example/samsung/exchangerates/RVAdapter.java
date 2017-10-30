package com.example.samsung.exchangerates;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Samsung on 25.10.2017.
 */

public class RVAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<Data> dataList;
    private static int tag = 0;
    private Fragment fragment;

    public static int getTag() {
        return tag;
    }

    public RVAdapter(List<Data> dataList, Fragment fragment) {
        this.dataList = dataList;
        this.fragment = fragment;
    }


    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_fragment_item, parent, false);
        DataViewHolder dataViewHolder = new DataViewHolder(v);
        return dataViewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DataViewHolder) holder).currency.setText(String.valueOf(dataList.get(position).getCurrency()));
        ((DataViewHolder) holder).rate.setText(String.valueOf(dataList.get(position).getRate()));

        DataViewHolder dataViewHolder = (DataViewHolder) holder;
        dataViewHolder.root.setTag(position);
        ((DataViewHolder) holder).root.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View view) {
        tag = (int) view.getTag();
        MyDialogFragment dialog = new MyDialogFragment();
        dialog.show(fragment.getFragmentManager(), "Fragment");
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private View root;
        private TextView currency;
        private TextView rate;

        public DataViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            currency = itemView.findViewById(R.id.currencyId);
            rate = itemView.findViewById(R.id.rateId);
        }
    }

}
