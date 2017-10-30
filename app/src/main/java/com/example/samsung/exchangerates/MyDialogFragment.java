package com.example.samsung.exchangerates;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.samsung.exchangerates.FirstFragmentPackage.FirstFragment;

import static com.example.samsung.exchangerates.DatabaseHelper.TABLE;

/**
 * Created by Samsung on 29.10.2017.
 */

public class MyDialogFragment extends DialogFragment {
    private Button btnYes;
    private Button btnNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_fragment, container, false);
        getDialog().setTitle("DIALOG");

        btnYes = v.findViewById(R.id.btnYes);
        btnNo = v.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data d = DataCache.getInstance().dataList.remove(RVAdapter.getTag());
                ((MainActivity)getActivity()).db.delete(TABLE, "currency == ?", new String[]{d.getCurrency()});
                ((FirstFragment)getFragmentManager().findFragmentById(R.id.fragmentContainer)).getAdapter().notifyDataSetChanged();
            getDialog().dismiss();
        }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return v;
    }
}
