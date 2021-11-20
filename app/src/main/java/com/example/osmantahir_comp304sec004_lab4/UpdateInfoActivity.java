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

public class UpdateInfoActivity extends AppCompatActivity
{
    EditText fname,lname,room,dept;
    Button update;
    PatientModel pm;
    int pid;
    SharedPreferences sp;
    PatientViewModel patientViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        setTitle("Student Information");
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        room=findViewById(R.id.room);
        dept=findViewById(R.id.dept);
        update=findViewById(R.id.update);
        sp=getSharedPreferences("pref",MODE_PRIVATE);

        PatientModel myList = (PatientModel) getIntent().getParcelableExtra("object");
        patientViewModel= ViewModelProviders.of(this).get(PatientViewModel.class);

        fname.setText(myList.getFirstname());
        lname.setText(myList.getLastname());
        dept.setText(myList.getDepartment());
        room.setText(myList.getRoom()+"");
        pid=myList.getPatientid();
        pm=new PatientModel();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().isEmpty())
                {
                    fname.setError("First name Required");
                }
                else if(lname.getText().toString().isEmpty())
                {
                    lname.setError("Last name Required");
                }
                else if(dept.getText().toString().isEmpty())
                {
                    dept.setError("Department Required");
                }
                else  if(room.getText().toString().isEmpty())
                {
                    room.setError("Room No. Required");
                }
                else {
                    pm.setNurseid(sp.getInt("nid", 0));
                    pm.setFirstname(fname.getText().toString());
                    pm.setLastname(lname.getText().toString());
                    pm.setDepartment(dept.getText().toString());
                    pm.setRoom(Integer.parseInt(room.getText().toString()));
                    pm.setPatientid(pid);

                    patientViewModel.Update(pm);

                    patientViewModel.UpdatepatientInfo().observe(UpdateInfoActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(@Nullable Integer integer) {
                            if (integer == 1) {
                                Toast.makeText(UpdateInfoActivity.this, "Student Update Success", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UpdateInfoActivity.this, "Student Update Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,PatientActivity.class);
        startActivity(i);
    }
}

