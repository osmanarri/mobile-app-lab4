package com.example.osmantahir_comp304sec004_lab4;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NurseRegistrationActivity extends AppCompatActivity {

    EditText fname,lname,pass,dept;
    Button reg;
    NurseModel nm;
    NurseViewModel nurseViewModel;
    ArrayList<NurseModel> nar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_nurse_registration);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        dept=findViewById(R.id.department);
        pass=findViewById(R.id.pass);
        reg=findViewById(R.id.reg);
        nar=new ArrayList<>();
        nm=new NurseModel();
        nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fname.getText().toString().isEmpty()) {
                    fname.setError("First Name Required");
                } else if (lname.getText().toString().isEmpty()) {
                    lname.setError("Last Name Required");
                } else if (dept.getText().toString().isEmpty()) {
                    dept.setError("Department Name Required");
                } else if (pass.getText().toString().isEmpty()) {
                    pass.setError("Password Required");
                } else {
                    nm.setFirstname(fname.getText().toString());
                    nm.setLastname(lname.getText().toString());
                    nm.setDepartment(dept.getText().toString());
                    nm.setPassword(pass.getText().toString());
                    nurseViewModel.insert(nm);

                }
            }
        });
        nurseViewModel.getInsertresult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer result) {
                if (result == 1) {
                    Toast.makeText(NurseRegistrationActivity.this, "Professor Registration Done", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(NurseRegistrationActivity.this,NurseLoginActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(NurseRegistrationActivity.this, "Error in  Registration", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}