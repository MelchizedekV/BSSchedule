package com.example.user.bsschedule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView submit;
    EditText date;
    EditText place;
    String sdate, splace;
    FirebaseFirestore db;
    CollectionReference Schedule;
    ArrayList<String> placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retreive_data);
        submit = (TextView) findViewById(R.id.submitbtn);
        date = findViewById(R.id.etdate);
        place = findViewById(R.id.etplace);
        db = FirebaseFirestore.getInstance();
        placeList = new ArrayList<>();
       // getSpinnerData();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FirestoreReceiverclass.class);

                sdate = date.getText().toString();
                splace = place.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("sdate", sdate);
                bundle.putString("splace", splace);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });
    }

    private void getSpinnerData() {

        db.collection("Schedule")
                .whereArrayContains("DATE", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                document.getData();
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            //   Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}