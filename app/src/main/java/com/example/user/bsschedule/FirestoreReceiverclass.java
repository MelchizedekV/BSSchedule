package com.example.user.bsschedule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirestoreReceiverclass extends AppCompatActivity {

    String sdate, splace;
    CollectionReference Schedule;
    Map<String, Object> map = new HashMap<>();
    ImageView imgbckicon;
    TextView date, day, place, speaker, subject;
    TextView txtdate, txtday, txtplace, txtsub, txtspeaker;
    TextView nodata;
    View viewseperator1,viewseperator2,viewseperator3,viewseperator4,viewseperator5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpage);
        intializtion();
        getValue();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Schedule = db.collection("Schedule");

        getDataFromFirebase();
    }

    private void getValue()
    {
        Bundle bundle=getIntent().getExtras();

        sdate=bundle.getString("sdate");
        splace=bundle.getString("splace");
    }

    private void intializtion() {
        date = findViewById(R.id.date);
        day = findViewById(R.id.day);
        place = findViewById(R.id.place);
        subject = findViewById(R.id.sub);
        speaker = findViewById(R.id.speaker);
        imgbckicon=findViewById(R.id.imgbckicon);

                txtdate=findViewById(R.id.txtdate);
                txtday=findViewById(R.id.txtday);
                txtplace=findViewById(R.id.txtplace);
                txtsub=findViewById(R.id.txtsub);
                txtspeaker=findViewById(R.id.txtspeaker);

        nodata=findViewById(R.id.nodata);
        viewseperator1=findViewById(R.id.viewseperator1);
        viewseperator2=findViewById(R.id.viewseperator2);
        viewseperator3=findViewById(R.id.viewseperator3);
        viewseperator4=findViewById(R.id.viewseperator4);
        viewseperator5=findViewById(R.id.viewseperator5);
        imgbckicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getDataFromFirebase() {

        Query query = Schedule.whereEqualTo("DATE", sdate).whereEqualTo("PLACE", splace);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    if (!task.getResult().isEmpty())
                    {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            map = queryDocumentSnapshot.getData();
                            date.setVisibility(View.VISIBLE);
                            day.setVisibility(View.VISIBLE);
                            place.setVisibility(View.VISIBLE);
                            speaker.setVisibility(View.VISIBLE);
                            subject.setVisibility(View.VISIBLE);
                            txtdate.setVisibility(View.VISIBLE);
                            txtday.setVisibility(View.VISIBLE);
                            txtplace.setVisibility(View.VISIBLE);
                            txtsub.setVisibility(View.VISIBLE);
                            txtspeaker.setVisibility(View.VISIBLE);
                            nodata.setVisibility(View.GONE);
                            viewseperator1.setVisibility(View.VISIBLE);
                            viewseperator2.setVisibility(View.VISIBLE);
                            viewseperator3.setVisibility(View.VISIBLE);
                            viewseperator4.setVisibility(View.VISIBLE);
                            viewseperator5.setVisibility(View.VISIBLE);
                            String msdate = (String) map.get("DATE");
                            String msplace = (String) map.get("PLACE");
                            String msday = (String) map.get("DAY");
                            String msSubject = (String) map.get("SUBJECT");
                            String msspeaker = (String) map.get("SPEAKER");

                            date.setText(msdate);
                            day.setText(msday);
                            place.setText(msplace);
                            speaker.setText(msspeaker);
                            subject.setText(msSubject);

                        }
                    } else {

                        date.setVisibility(View.GONE);
                        day.setVisibility(View.GONE);
                        place.setVisibility(View.GONE);
                        speaker.setVisibility(View.GONE);
                        subject.setVisibility(View.GONE);

                        txtdate.setVisibility(View.GONE);
                        txtday.setVisibility(View.GONE);
                        txtplace.setVisibility(View.GONE);
                        txtsub.setVisibility(View.GONE);
                        txtspeaker.setVisibility(View.GONE);

                        nodata.setVisibility(View.VISIBLE);
                        viewseperator1.setVisibility(View.GONE);
                        viewseperator2.setVisibility(View.GONE);
                        viewseperator3.setVisibility(View.GONE);
                        viewseperator4.setVisibility(View.GONE);
                        viewseperator5.setVisibility(View.GONE);
                    }
                }
            }


        });

    }

}