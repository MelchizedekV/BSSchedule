package com.example.user.bsschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RetrieveDataAdapter extends BaseAdapter
{
    Context context;
    String[] countryNames;
    LayoutInflater inflter;


    public RetrieveDataAdapter(Context context, String[] countryNames)
    {
     this.context=context;
     this.countryNames=countryNames;
     inflter = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {

        return countryNames.length;
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
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        view = inflter.inflate(R.layout.retrieve_single_row, null);
        TextView single_txt=view.findViewById(R.id.retrieve_single_row_place);
        single_txt.setText(countryNames[i]);
        return view;
    }
}
