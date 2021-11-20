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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NurseLoginActivity extends AppCompatActivity {

    EditText uid,pwd;
    Button login;
    TextView reg;
    NurseViewModel nurseViewModel;
    NurseModel nurseModel;
    ListView lv;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_login);


        uid=findViewById(R.id.uid);
        pwd=findViewById(R.id.pwd);
        login=findViewById(R.id.login);
        sp=getSharedPreferences("pref",MODE_PRIVATE);
        reg=findViewById(R.id.reg);

        if(sp.contains("nid"))
        {
            Intent i=new Intent(NurseLoginActivity.this,PatientActivity.class);
            startActivity(i);
        }

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(NurseLoginActivity.this,NurseRegistrationActivity.class);
                startActivity(i);
            }
        });

        nurseModel=new NurseModel();
        nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uid.getText().toString().isEmpty())
                {
                    uid.setError("User Id Required");
                }
                else if(pwd.getText().toString().isEmpty())
                {
                    pwd.setError("Password Required");
                }
                else {
                    nurseViewModel.getLogin(Integer.parseInt(uid.getText().toString()), pwd.getText().toString());

                    nurseViewModel.getLoginRes().observe(NurseLoginActivity.this, new Observer<NurseModel>() {
                        @Override
                        public void onChanged(@Nullable NurseModel nurseModel) {
                            if (nurseModel != null) {
                                Toast.makeText(NurseLoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor ed = sp.edit();
                                ed.putInt("nid", nurseModel.getNurseid());
                                ed.putString("nnmae", nurseModel.getFirstname());
                                ed.apply();

                                Intent i = new Intent(NurseLoginActivity.this, PatientActivity.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(NurseLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        nurseViewModel.getInsertresult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if(integer==1)
                {
                    Toast.makeText(NurseLoginActivity.this, "Registration Suucessfully done", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(NurseLoginActivity.this, "Error Registration Nurse", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}