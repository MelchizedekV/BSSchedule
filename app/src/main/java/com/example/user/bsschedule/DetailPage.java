package com.example.user.bsschedule;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPage extends Fragment {

    List<ModelClass> detailList;
    TextView date, place, day, speaker, subject;
    ImageView backbtn;
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
        backbtn=view.findViewById(R.id.backarrow);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        ModelClass modelClass = detailList.get(0);


        date.setText(modelClass.getDate());
        day.setText(modelClass.getDay());
        place.setText(modelClass.getPlace());
        subject.setText(modelClass.getSubject());
        speaker.setText(modelClass.getSpeaker());


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
