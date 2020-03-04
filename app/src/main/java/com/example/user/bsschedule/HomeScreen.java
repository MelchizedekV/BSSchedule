package com.example.user.bsschedule;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {

    TextView txtemailID, txtpassword, txtguest, txtNext;
    EditText edtxtEmailID, edtxtPassword;
    Boolean isEmailFocus = false, isPasswordFocus = false;
    RelativeLayout homescreen;
    FrameLayout container;
    FragmentManager fragmentManager;
    AddData addData;
    FragmentTransaction fragmentTransaction;
    AlertDialog.Builder closeAppPopUp;
    private FirebaseAuth mAuth;
    BottomNavigationView navigationView;
    String EmailID, Password;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        txtemailID = findViewById(R.id.txt_homescreen_email);
        txtpassword = findViewById(R.id.txt_homescreen_pass);
        txtguest = findViewById(R.id.txt_homescreen_guest_user);
        txtNext = findViewById(R.id.txt_homescreen_nextbtn);
        edtxtEmailID = findViewById(R.id.edtxt_homescreen_email);
        edtxtPassword = findViewById(R.id.edtxt_homescreen_password);
        navigationView = findViewById(R.id.bottom_naviagtion);
        container = findViewById(R.id.container);
        homescreen = findViewById(R.id.rel_lay_homescreen_header);
        relativeLayout = findViewById(R.id.home_screen_root_lay);
        closeAppPopUp = new AlertDialog.Builder(HomeScreen.this);
        mAuth = FirebaseAuth.getInstance();
        edtxtEmailID.setText("Bsscriptures@gmail.com");
        edtxtPassword.setText("bsscripture1874");
        emailIDFocusListner();
        passwordFocusListner();
        emailidTxtListner();
        passTxtListner();
        btnNextListner();
        guestUserListner();

    }


    void guestUserListner() {
        txtguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RetrieveDataFragment();

            }
        });
    }

    void RetrieveDataFragment() {
        Intent retrieveDataNew =new Intent(HomeScreen.this,RetrieveDataNew.class);
        startActivity(retrieveDataNew);

    }

    public void btnNextListner() {
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EmailID = edtxtEmailID.getText().toString();
                Password = edtxtPassword.getText().toString();
                if ((EmailID != null && !EmailID.isEmpty()) && (Password != null && !Password.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(EmailID, Password).addOnCompleteListener(HomeScreen.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                RetrieveDataFragment();
                            } else {
                                callSnackbar("Enter Valid Email ID or Password");
                            }
                        }
                    });
                } else {

                    callSnackbar("Field should not be empty");
                }

            }
        });

    }




    void passTxtListner() {
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

    void emailidTxtListner() {
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

    @Override
    public void onBackPressed() {

        closeAppPopUp.setMessage("Do you want to close the application");
        closeAppPopUp .setTitle("BS Scheduler");
        closeAppPopUp.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                        HomeScreen.super.onBackPressed();
                    }
                });
        closeAppPopUp.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                   dialogInterface.dismiss();

            }
            });
        closeAppPopUp.show();

    }

    public void callSnackbar(String Message) {
        Snackbar snackbar = Snackbar.make(relativeLayout, Message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
