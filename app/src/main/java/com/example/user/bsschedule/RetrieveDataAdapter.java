package com.example.user.bsschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RetrieveDataAdapter extends BaseAdapter {
    Context context;
    List<String> dataList;
    LayoutInflater inflter;
    RetrieveDataNew retrieveData;
    Boolean isPlaceList;

    public RetrieveDataAdapter(Context context, Set<String> placeList, RetrieveDataNew retrieveData, Boolean isPlaceList) {
        this.context = context;
        this.dataList =new ArrayList<>(placeList) ;
        inflter = (LayoutInflater.from(context));
        this.retrieveData = retrieveData;
        this.isPlaceList =isPlaceList;
    }





    @Override
    public int getCount() {

        return dataList.size();
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
        single_txt.setText(dataList.get(i));
        single_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place = dataList.get(i);

                if(isPlaceList)
                    retrieveData.placePopulater(place);
                else
                    retrieveData.timePopulater(place);

            }
        });
        return view;
    }
}
