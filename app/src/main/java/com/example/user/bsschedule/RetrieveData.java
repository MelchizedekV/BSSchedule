package com.example.user.bsschedule;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetrieveData extends Fragment {

    TextView txtdate, submitBtn;
    EditText etplace;
    String readDate, readPlace;
    DatePickerDialog datePickerDialog;
    Context context;
    FragmentManager fragmentManager;
    int year, month, date;
    List<ModelClass> list;
    List<String> placeList;
    FirebaseFirestore db;
    FragmentTransaction fragmentTransaction;
    CollectionReference Schedule;
    AlertDialog alert;
    DetailPage detailPage;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;
    RelativeLayout retrieveHeader;
    TextView spinnerPlace;
    FrameLayout Container;
    AlertDialog.Builder builder;

    public RetrieveData() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.retreive_data, container, false);
        txtdate = view.findViewById(R.id.txtdate);
        submitBtn = view.findViewById(R.id.submitbtn);
        retrieveHeader = view.findViewById(R.id.retrieveDataHeader);
        Container = view.findViewById(R.id.container);
        spinnerPlace = view.findViewById(R.id.txtplace);
        relativeLayout = view.findViewById(R.id.retrieve_data_root_layout);
        progressBar=view.findViewById(R.id.retrieve_data_progress_bar);
        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        placeList = new ArrayList<>();

        dateListner();

        submitBtnListner();
        placeQuery();
        spinnerPlaceListner();
        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    private void placeQuery() {
        ViewVisibility(true);
        Schedule = db.collection("Schedule");
        Schedule.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> documentSnapshots = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : documentSnapshots) {
                        ModelClass modelClass = d.toObject(ModelClass.class);
                        placeList.add(modelClass.getPlace());
                    }
                    ViewVisibility(false);
                    placeList.removeAll(Collections.singleton(null));


                } else {
                    callSnackbar("No Data Available");
                }

            }
        });
    }

    private void ViewVisibility(Boolean aBoolean)
    {
        if (aBoolean.equals(true))
        {
            progressBar.setVisibility(View.VISIBLE);
            retrieveHeader.setVisibility(View.GONE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            retrieveHeader.setVisibility(View.VISIBLE);
        }
        }

    private void spinnerPlaceListner() {
        spinnerPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(context, R.style.customAlert);


                RetrieveDataAdapter retrieveDataAdapter = new RetrieveDataAdapter(context, placeList, RetrieveData.this);
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        Schedule = db.collection("Schedule");
        Query query = Schedule.whereEqualTo("date", readDate).whereEqualTo("place", readPlace);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> documentSnapshots = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : documentSnapshots) {
                        ModelClass modelClass = d.toObject(ModelClass.class);
                        list.add(modelClass);
                    }
                    callDetailPage();

                } else {
                    callSnackbar("No Data Available");
                }

            }
        });


    }

    private void callDetailPage() {
        retrieveHeader.setVisibility(View.GONE);
        Container.setVisibility(View.VISIBLE);
        detailPage = new DetailPage();
        fragmentManager = getChildFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("detailList", (ArrayList<? extends Parcelable>) list);
        detailPage.setArguments(bundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, detailPage).commit();
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
        datePickerDialog = new DatePickerDialog(context, R.style.DatepickerTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                txtdate.setText(String.format("%d.%d.%d", date, month + 1, year));

            }
        }, year, month, date);
        datePickerDialog.show();


    }

    public void callSnackbar(String Message) {
        Snackbar snackbar = Snackbar.make(relativeLayout, Message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

 public void   backarrowListner()
    {
        fragmentManager.popBackStack();
    }
    void placePopulater(String place) {
        alert.dismiss();
        spinnerPlace.setText(place);
    }
}
