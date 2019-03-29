package com.example.user.bsschedule;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddData extends Fragment {
    TextView txtDay, txtDate, txtSub, txtPlace, txtSpeaker, txtSubmit;
    EditText edtxtDay, edtxtDate, edtxtSub, edtxtSpeaker, edtxtplace;
    Boolean isDateFocus = false, isDayFocus = false, isPlaceFocus = false, isSpeakerFocus = false, isSubFocus = false;
    FirebaseFirestore db ;
    Context context;


    public AddData() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      this.context=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_data, container, false);
        txtDate = view.findViewById(R.id.txt_addData_Date);
        txtDay = view.findViewById(R.id.txt_addData_day);
        txtPlace = view.findViewById(R.id.txt_addData_place);
        txtSpeaker = view.findViewById(R.id.txt_addData_speaker);
        txtSub = view.findViewById(R.id.txt_addData_sub);
        txtSubmit=view.findViewById(R.id.txt_addData_submit);
        db = FirebaseFirestore.getInstance();

        edtxtDate = view.findViewById(R.id.edtxt_addData_date);
        edtxtDay = view.findViewById(R.id.edtxt_addData_day);
        edtxtplace = view.findViewById(R.id.edtxt_addData_place);
        edtxtSpeaker = view.findViewById(R.id.edtxt_addData_speaker);
        edtxtSub = view.findViewById(R.id.edtxt_addData_sub);
        dateFocusListner();
        dayFocusListner();
        placeFocusListner();
        speakerFocusListner();
        subFocusListner();
        dateListner();
        dayListner();
        submitListner();
        placeListner();
        speakerListner();
        subListner();
        return view;
    }

    private void submitListner()
    {
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AddDataToServer();
            }
        });
    }

    private void AddDataToServer()
    {
        if (!edtxtDate.getText().toString().isEmpty() && !edtxtDay.getText().toString().isEmpty() && !edtxtplace.getText().toString().isEmpty() && !edtxtSub.getText().toString().isEmpty() && !edtxtSpeaker.getText().toString().isEmpty()) {
            Map<String, Object> data = new HashMap<>();
            data.put("date", edtxtDate.getText().toString());
            data.put("day", edtxtDay.getText().toString());
            data.put("place", edtxtplace.getText().toString());
            data.put("speaker", edtxtSpeaker.getText().toString());
            data.put("subject", edtxtSub.getText().toString());
            db.collection("Schedule").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(context, "Data added Successfully", Toast.LENGTH_LONG).show();
                    edtxtDate.setText("");
                    edtxtDay.setText("");
                    edtxtplace.setText("");
                    edtxtSpeaker.setText("");
                    edtxtSub.setText(" ");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Please Try again", Toast.LENGTH_LONG).show();
                        }
                    });
        }
        else
        {
            Toast.makeText(context, "Fields Should not be empty", Toast.LENGTH_LONG).show();
        }
    }

    private void subListner() {
        edtxtSub.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 1) {
                    isSubFocus = true;
                } else {
                    isSubFocus = false;
                }

            }
        });

    }

    private void speakerListner() {
        edtxtSpeaker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 1) {
                    isSpeakerFocus = true;
                } else {
                    isSpeakerFocus = false;
                }

            }
        });

    }

    private void placeListner() {
        edtxtplace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 1) {
                    isPlaceFocus = true;
                } else {
                    isPlaceFocus = false;
                }

            }
        });

    }

    private void dayListner() {
        edtxtDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 1) {
                    isDayFocus = true;
                } else {
                    isDayFocus = false;
                }

            }
        });

    }

    void dateListner() {
        edtxtDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() >= 1) {
                    isDateFocus = true;
                } else {
                    isDateFocus = false;
                }

            }
        });
    }

    void dateFocusListner() {
        edtxtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus || isDateFocus) {
                    txtDate.setVisibility(View.VISIBLE);
                    edtxtDate.setHint("");
                } else {
                    txtDate.setVisibility(View.GONE);
                    edtxtDate.setHint(getResources().getString(R.string.Date));
                }

            }
        });
    }

    private void dayFocusListner() {
        edtxtDay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus || isDayFocus) {
                    txtDay.setVisibility(View.VISIBLE);
                    edtxtDay.setHint("");
                } else {
                    txtDay.setVisibility(View.GONE);
                    edtxtDay.setHint(getResources().getString(R.string.Day));

                }

            }
        });
    }

    private void placeFocusListner() {
        edtxtplace.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus || isPlaceFocus) {
                    txtPlace.setVisibility(View.VISIBLE);
                    edtxtplace.setHint("");
                } else {
                    txtPlace.setVisibility(View.GONE);
                    edtxtplace.setHint(getResources().getString(R.string.Place));
                }

            }
        });
    }

    private void speakerFocusListner() {
        edtxtSpeaker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus || isSpeakerFocus) {
                    txtSpeaker.setVisibility(View.VISIBLE);
                    edtxtSpeaker.setHint("");
                } else {
                    txtSpeaker.setVisibility(View.GONE);
                    edtxtSpeaker.setHint(getResources().getString(R.string.Speaker));

                }

            }
        });
    }

    private void subFocusListner() {
        edtxtSub.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus || isSubFocus) {
                    txtSub.setVisibility(View.VISIBLE);
                    edtxtSub.setHint("");
                } else {
                    txtSub.setVisibility(View.GONE);
                    edtxtSub.setHint(getResources().getString(R.string.Sub));
                }

            }
        });
    }





}
