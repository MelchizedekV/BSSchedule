package com.example.user.bsschedule;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetrieveData extends Fragment {

TextView txtdate;
    DatePickerDialog datePickerDialog;
   Context context;
   int year,month,date;
    public RetrieveData() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
 this.context=context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.retreive_data, container, false);
        txtdate=view.findViewById(R.id.txtdate);
        dateListner();
        return view;
    }

    private void dateListner()
    {
        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Calendar calendar=Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                date=calendar.get(Calendar.DATE);
                datePickerDialog();

            }
        });

    }

    private void datePickerDialog()
    {
            datePickerDialog=new DatePickerDialog(context,R.style.DatepickerTheme, new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                    txtdate.setText(date + ". "+(month+1) + "." +year);

                }
            },year,month,date);
            datePickerDialog.show();


    }

}
