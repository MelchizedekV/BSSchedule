package com.example.user.bsschedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPage extends Fragment {

    List<ModelClass> detailList;
    TextView date, place, day, speaker, subject;

    public DetailPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailpage, container, false);
        date = view.findViewById(R.id.date);
        place = view.findViewById(R.id.place);
        day = view.findViewById(R.id.day);
        subject = view.findViewById(R.id.sub);
        speaker = view.findViewById(R.id.speaker);
        detailList = new ArrayList<>();
        detailList = getArguments().getParcelableArrayList("detailList");

        ModelClass modelClass = detailList.get(0);

        date.setText(modelClass.getDate());
        day.setText(modelClass.getDay());
        place.setText(modelClass.getPlace());
        subject.setText(modelClass.getSubject());
        speaker.setText(modelClass.getSpeaker());


        return view;
    }

}
