package com.example.user.bsschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailPageNew extends AppCompatActivity {

    List<ModelClass> detailList;
    TextView date, place, day, speaker, subject,time,classType;
    ImageView backbtn;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpage);
        date = findViewById(R.id.date);
        place = findViewById(R.id.place);
        backbtn= findViewById(R.id.backarrow);
        day = findViewById(R.id.day);
        subject = findViewById(R.id.sub);
        speaker = findViewById(R.id.speaker);
        time= findViewById(R.id.time);
        classType= findViewById(R.id.classType);
        detailList = new ArrayList<>();
        bundle =getIntent().getExtras();
        detailList =bundle.getParcelableArrayList("detailList");
        ModelClass modelClass = detailList.get(0);

        date.setText(modelClass.getDate());
        day.setText(modelClass.getDay());
        place.setText(modelClass.getPlace());
        subject.setText(modelClass.getSubject());
        speaker.setText(modelClass.getSpeaker());
        time.setText(modelClass.getTime());
        classType.setText(modelClass.getClassType());

        backbtnListner();

    }

    private void backbtnListner() {
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}


