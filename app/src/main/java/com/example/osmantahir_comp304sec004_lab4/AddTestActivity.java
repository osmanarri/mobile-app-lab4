package com.example.osmantahir_comp304sec004_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddTestActivity extends AppCompatActivity {

    EditText tempe,bpl,bph;
    Button submit;
    Spinner spin;
    TestModel tm;
    TestViewModel testViewModel;
    ArrayList<String> par;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        setTitle("Add Test");
        bph=findViewById(R.id.bph);
        bpl=findViewById(R.id.bpl);
        tempe=findViewById(R.id.tempe);
        submit=findViewById(R.id.submit);
        spin=findViewById(R.id.spin);
        tm=new TestModel();
        sp=getSharedPreferences("pref",MODE_PRIVATE);
        testViewModel= ViewModelProviders.of(this).get(TestViewModel.class);
        par=new ArrayList<>();
        par.add("Select Student");
        testViewModel.getAllPatientlist().observe(this, new Observer<List<PatientModel>>() {
            @Override
            public void onChanged(@Nullable List<PatientModel> result) {
                par.clear();
                par.add("Select Student");
                for(PatientModel person : result) {
                    par.add(person.getPatientid()+"."+person.getFirstname());

                }
            }
        });
        @SuppressWarnings("unchecked")
        ArrayAdapter ad=new ArrayAdapter(AddTestActivity.this,android.R.layout.simple_list_item_1,par);
        spin.setAdapter(ad);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val;
                if(spin.getSelectedItemPosition()==0) {
                    Toast.makeText(AddTestActivity.this, "Select Student First", Toast.LENGTH_SHORT).show();
                }
                if(bpl.getText().toString().isEmpty())
                {
                    bpl.setError("BPL Required");
                }
                else if(bph.getText().toString().isEmpty())
                {
                    bph.setError("BPH Required");
                }
                else if(tempe.getText().toString().isEmpty())
                {
                    tempe.setError("Temperature Required");
                }
                else {
                    val=spin.getSelectedItem().toString().substring(0,spin.getSelectedItem().toString().indexOf("."));
                    tm.setNurseid(sp.getInt("nid",0));
                    tm.setPatientid(Integer.parseInt(val));
                    tm.setTemperature(tempe.getText().toString());
                    tm.setBHP(bph.getText().toString());
                    tm.setBPL(bpl.getText().toString());

                    testViewModel.inserttest(tm);

                    testViewModel.gettestresult().observe(AddTestActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(@Nullable Integer integer) {
                            if(integer==1)
                            {
                                Toast.makeText(AddTestActivity.this, " Test Added Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(AddTestActivity.this, "Error in Add Test", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(AddTestActivity.this,PatientActivity.class);
        startActivity(i);
    }
}