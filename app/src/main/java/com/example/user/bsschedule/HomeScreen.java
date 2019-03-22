package com.example.user.bsschedule;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    RetrieveData retrieveData;
    private FirebaseAuth mAuth;
    BottomNavigationView navigationView;
    String EmailID,Password;

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
        navigationView=findViewById(R.id.bottom_naviagtion);
        container = findViewById(R.id.container);
        homescreen = findViewById(R.id.rel_lay_homescreen_header);
        mAuth = FirebaseAuth.getInstance();
        emailIDFocusListner();
        passwordFocusListner();
        emailidTxtListner();
        passTxtListner();
        btnNextListner();
        guestUserListner();
        bottomNavigationLisner();
    }

     public void bottomNavigationLisner()
    {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.add_data:
                        AddDataFragment();
                        visibilityHandler(false);
                        navigationView.setVisibility(View.VISIBLE);
                        break;
                    case R.id.Retrieve_Data:
                        RetrieveDataFragment();
                        visibilityHandler(false);
                        navigationView.setVisibility(View.VISIBLE);
                }

                return true;
            }

                 });
    }

     void guestUserListner() {
        txtguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibilityHandler(false);
                navigationView.setVisibility(View.GONE);
                RetrieveDataFragment();

            }
        });
    }
     void RetrieveDataFragment()
    {
        retrieveData=new RetrieveData();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,retrieveData).commit();
    }

     void AddDataFragment()
    {
        addData=new AddData();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,addData).commit();
    }

  public void btnNextListner() {
        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EmailID=edtxtEmailID.getText().toString();
                Password=edtxtPassword.getText().toString();
                if((EmailID!=null&&!EmailID.isEmpty())&&(Password!=null&&!Password.isEmpty()))
                {
                    mAuth.signInWithEmailAndPassword(EmailID, Password).addOnCompleteListener(HomeScreen.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                AddDataFragment();
                                visibilityHandler(false);
                                navigationView.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(HomeScreen.this, "Enter Valid Email ID or Password", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(HomeScreen.this, "Field should not be empty", Toast.LENGTH_LONG).show();

                }

            }
        });

    }



     void visibilityHandler(boolean isActivity) {
        if (isActivity) {
            homescreen.setVisibility(View.VISIBLE);
            container.setVisibility(View.GONE);

        } else {
            container.setVisibility(View.VISIBLE);
            homescreen.setVisibility(View.GONE);

        }
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


}
