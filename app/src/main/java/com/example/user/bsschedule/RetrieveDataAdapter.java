package com.example.user.bsschedule;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RetrieveDataAdapter extends BaseAdapter {
    Context context;
    List<String> placeList;
    LayoutInflater inflter;
    RetrieveData retrieveData;

    public RetrieveDataAdapter(Context context, List<String> placeList, RetrieveData retrieveData) {
        this.context = context;
        this.placeList = placeList;
        inflter = (LayoutInflater.from(context));
        this.retrieveData = retrieveData;
    }


    @Override
    public int getCount() {

        return placeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.retrieve_single_row, null);
        TextView single_txt = view.findViewById(R.id.retrieve_single_row_place);
        single_txt.setText(placeList.get(i));
        single_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String place = placeList.get(i);
                retrieveData.placePopulater(place);
            }
        });
        return view;
    }
}
