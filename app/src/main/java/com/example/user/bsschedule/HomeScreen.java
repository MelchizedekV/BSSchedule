package com.example.user.bsschedule;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    TextView txtemailID, txtpassword, txtskip, txtNext;
    EditText edtxtEmailID, edtxtPassword;
    Boolean isEmailFocus = false, isPasswordFocus = false;
    Boolean isActivity=true;
    RelativeLayout homescreen;
    FrameLayout container;
    FragmentManager fragmentManager;
    AddData addData = new AddData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        txtemailID = findViewById(R.id.txt_homescreen_email);
        txtpassword = findViewById(R.id.txt_homescreen_pass);
        txtskip = findViewById(R.id.txt_homescreen_skip);
        txtNext = findViewById(R.id.txt_homescreen_nextbtn);
        edtxtEmailID = findViewById(R.id.edtxt_homescreen_email);
        edtxtPassword = findViewById(R.id.edtxt_homescreen_password);
        fragmentManager = getSupportFragmentManager();
        container = findViewById(R.id.container);
        homescreen=findViewById(R.id.rel_lay_homescreen_header);
        emailIDFocusListner();
        passwordFocusListner();
        emailidTxtListner();
        passTxtListner();
        btnNextListner();
    }

    private void btnNextListner() {
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isActivity=false;
                visibilityHandler();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container, addData).commit();

            }
        });

    }

    private void visibilityHandler()
    {
        if(isActivity)
        {
            homescreen.setVisibility(View.VISIBLE);
            container.setVisibility(View.GONE);
        }
        else
        {
            container.setVisibility(View.VISIBLE);
            homescreen.setVisibility(View.GONE);
        }
    }

    private void passTxtListner() {
        edtxtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 1) {
                    isPasswordFocus = true;

                } else {
                    isPasswordFocus = false;
                }
            }
        });
    }

    private void emailidTxtListner() {
        edtxtEmailID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 1) {
                    isEmailFocus = true;
                } else {
                    isEmailFocus = false;
                }

            }
        });
    }

    void passwordFocusListner() {
        edtxtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus || isPasswordFocus) {
                    txtpassword.setVisibility(View.VISIBLE);
                    edtxtPassword.setHint("");
                } else {
                    txtpassword.setVisibility(View.GONE);
                    edtxtPassword.setHint(getResources().getString(R.string.password));

                }

            }
        });
    }

    void emailIDFocusListner() {
        edtxtEmailID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus || isEmailFocus) {
                    txtemailID.setVisibility(View.VISIBLE);
                    edtxtEmailID.setHint("");
                } else {
                    txtemailID.setVisibility(View.GONE);
                    edtxtEmailID.setHint(getResources().getString(R.string.emailid));
                }
            }
        });

    }


}
