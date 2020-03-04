package com.example.user.bsschedule;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RetrieveDataNew extends AppCompatActivity {
    TextView txtdate, submitBtn;

    String readDate, readPlace,readTime;
    DatePickerDialog datePickerDialog;

    int year, month, date;
    List<ModelClass> list;

    List<String> placeList;
    List<String> timeList;
    FirebaseFirestore db;


    AlertDialog alert;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;
    RelativeLayout retrieveHeader;
    TextView spinnerPlace;
    TextView spinnerTime;
    FrameLayout Container;
    AlertDialog.Builder builder;
    Set<String> placeHashList;
    Set<String> timeHashList;
    DatabaseReference mDatabase;
    FirebaseDatabase database;
    SQliteHelper sQliteHelper;
    SQLiteDatabase sqLiteDatabase;
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retreive_data);
        txtdate = findViewById(R.id.txtdate);
        submitBtn = findViewById(R.id.submitbtn);
        retrieveHeader = findViewById(R.id.retrieveDataHeader);
        Container = findViewById(R.id.container);
        spinnerPlace = findViewById(R.id.txtplace);
        spinnerTime = findViewById(R.id.txtTime);
        relativeLayout = findViewById(R.id.retrieve_data_root_layout);
        progressBar=findViewById(R.id.retrieve_data_progress_bar);
        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        timeList =new ArrayList<>();
        placeList = new ArrayList<>();
        database =FirebaseDatabase.getInstance();
        sQliteHelper =new SQliteHelper(this);
//        new BackgroundOperation().execute();
        readEntireData();
        dateListner();
        submitBtnListner();
        spinnerPlaceListner();
        spinnerTimeListner();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    private void spinnerTimeListner() {
        spinnerTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(RetrieveDataNew.this, R.style.customAlert);
                timeHashList =new HashSet<>(timeList);
                RetrieveDataAdapter retrieveDataAdapter = new RetrieveDataAdapter(RetrieveDataNew.this, timeHashList, RetrieveDataNew.this,false);
                builder.setAdapter(retrieveDataAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                alert = builder.create();
                alert.show();

            }
        });
    }

    private void readEntireData() {
        progressBar.setVisibility(View.VISIBLE);
        sqLiteDatabase = sQliteHelper.getWritableDatabase();

        sqLiteDatabase.execSQL(SQliteHelper.DROP_TABLE);
        sqLiteDatabase.execSQL(SQliteHelper.CREATE_TABLE);

        mDatabase = database.getReference("Schedule").child("TmcZPGmEGsttGELsWBi6").child("Sheet1");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(sQliteHelper.CLASS_TYPE, data.child("Class Type").getValue().toString());
                    contentValues.put(sQliteHelper.DATE, data.child("Date").getValue().toString());
                    contentValues.put(sQliteHelper.DAY, data.child("Day").getValue().toString());
                    contentValues.put(sQliteHelper.PLACE, data.child("Place").getValue().toString());
                    contentValues.put(sQliteHelper.SPEAKER, data.child("Speaker").getValue().toString());
                    contentValues.put(sQliteHelper.SUBJECT, data.child("Subject").getValue().toString());
                    contentValues.put(sQliteHelper.TIME, data.child("Time").getValue().toString());
                    placeList.add(data.child("Place").getValue().toString());
                    timeList.add(data.child("Time").getValue().toString());
                    sqLiteDatabase.insert(sQliteHelper.TABLE_NAME, null, contentValues);

                    count = count + 1;

//                    Log.d("count",String.valueOf(count));
                }

                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
            private void spinnerPlaceListner() {
                spinnerPlace.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                builder = new AlertDialog.Builder(RetrieveDataNew.this, R.style.customAlert);
                placeHashList =new HashSet<>(placeList);
                RetrieveDataAdapter retrieveDataAdapter = new RetrieveDataAdapter(RetrieveDataNew.this, placeHashList, RetrieveDataNew.this,true);
                builder.setAdapter(retrieveDataAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                });
                alert = builder.create();
                alert.show();

            }
        });
    }
    private void submitBtnListner() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                readDataQuery();


            }
        });
    }
    private void readDataQuery() {
        readDate = txtdate.getText().toString();
        readPlace = spinnerPlace.getText().toString();
        readTime= spinnerTime.getText().toString();

        sqLiteDatabase=sQliteHelper.getWritableDatabase();

        String columns []={sQliteHelper.USER_ID,sQliteHelper.CLASS_TYPE,sQliteHelper.DATE,sQliteHelper.DAY,sQliteHelper.PLACE,sQliteHelper.SPEAKER,sQliteHelper.SUBJECT,sQliteHelper.TIME};
        String selection = sQliteHelper.DATE +"="+"'"+readDate+"'"
                +" AND " + sQliteHelper.PLACE +"="+"'"+readPlace+"'"
                +" AND " + sQliteHelper.TIME +"="+"'"+readTime+"'";
        Cursor cursor=sqLiteDatabase.query(sQliteHelper.TABLE_NAME,columns,selection,null,null,null,null);

        if(cursor.getCount() <= 0){
            callSnackbar("No data available");
            return;}
        list.clear();
        while (cursor.moveToNext())
        {

            ModelClass nameListPojo =new ModelClass();
            nameListPojo.setClassType(cursor.getString(cursor.getColumnIndex(SQliteHelper.CLASS_TYPE)));
            nameListPojo.setDate(cursor.getString(cursor.getColumnIndex(SQliteHelper.DATE)));
            nameListPojo.setDay(cursor.getString(cursor.getColumnIndex(SQliteHelper.DAY)));
            nameListPojo.setPlace(cursor.getString(cursor.getColumnIndex(SQliteHelper.PLACE)));
            nameListPojo.setTime(cursor.getString(cursor.getColumnIndex(SQliteHelper.TIME)));
            nameListPojo.setSpeaker(cursor.getString(cursor.getColumnIndex(SQliteHelper.SPEAKER)));
            nameListPojo.setSubject(cursor.getString(cursor.getColumnIndex(SQliteHelper.SUBJECT)));

            placeList.add(cursor.getString(cursor.getColumnIndex(SQliteHelper.PLACE)));

            list.add(nameListPojo);


        }
        if((readPlace!=null && !readPlace.isEmpty())&&(readDate!=null &&!readDate.isEmpty())&&(readTime!=null &&!readTime.isEmpty()))
            callDetailPage();
        else
            callSnackbar("Date,Time and Place cannot be empty");




    }
    private void dateListner() {
        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                date = calendar.get(Calendar.DATE);
                datePickerDialog();

            }
        });

    }
    private void datePickerDialog() {
        datePickerDialog = new DatePickerDialog(this, R.style.DatepickerTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                txtdate.setText(String.format("%d/%d/%d", date, month + 1, year));

            }
        }, year, month, date);
        datePickerDialog.show();


    }

    public void callSnackbar(String Message) {
        Snackbar snackbar = Snackbar.make(relativeLayout, Message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void callDetailPage() {
        Intent detailPage =new Intent(RetrieveDataNew.this,DetailPageNew.class);
        detailPage.putParcelableArrayListExtra("detailList", (ArrayList<? extends Parcelable>) list);
        startActivity(detailPage);
    }
    void placePopulater(String place) {
        alert.dismiss();
        spinnerPlace.setText(place);
    }
    void timePopulater(String time) {
        alert.dismiss();
        spinnerTime.setText(time);
    }
}
