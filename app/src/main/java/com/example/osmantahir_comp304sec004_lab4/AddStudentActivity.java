package com.example.osmantahir_comp304sec004_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    EditText fname, lname, dept, room;
    Button add;
    PatientModel patientModel;
    PatientViewModel patientViewModel;
    SharedPreferences sp;
    int nurseid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        setTitle("Add Student Information");
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        dept = findViewById(R.id.dept);
        room = findViewById(R.id.room);
        add = findViewById(R.id.addptn);
        patientModel = new PatientModel();
        sp = getSharedPreferences("pref", MODE_PRIVATE);
        nurseid = sp.getInt("nid", 0);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fname.getText().toString().isEmpty()) {
                    fname.setError("First name Required");
                } else if (lname.getText().toString().isEmpty()) {
                    lname.setError("last Name Required");
                } else if (dept.getText().toString().isEmpty()) {
                    dept.setError("Department Required");
                } else if (room.getText().toString().isEmpty()) {
                    room.setError("Room No. Required");
                } else {
                    patientModel.setFirstname(fname.getText().toString());
                    patientModel.setLastname(lname.getText().toString());
                    patientModel.setDepartment(dept.getText().toString());
                    patientModel.setRoom(Integer.parseInt(room.getText().toString()));
                    patientModel.setNurseid(nurseid);

                    patientViewModel.insertpatient(patientModel);

                    patientViewModel.getpatientresult().observe(AddStudentActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(@Nullable Integer integer) {
                            if (integer == 1) {
                                Toast.makeText(AddStudentActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(AddStudentActivity.this, "Error in Add Student", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, PatientActivity.class);
        startActivity(i);
    }
}