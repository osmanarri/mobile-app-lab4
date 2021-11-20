package com.example.osmantahir_comp304sec004_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NurseViewModel nurseViewModel;
    private NurseModel nurseModel;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewNurseListDisplay);
        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);
        nurseModel= new NurseModel();

        nurseViewModel.getNurselist().observe(this, new Observer<List<NurseModel>>()
        {
            @Override
            public void onChanged(@Nullable List<NurseModel> result) {

                String output="";

                if (!result.isEmpty())
                {
                    for (NurseModel nurseModel : result) {
                        output += "#ID --> " + nurseModel.getNurseid() + " #Name --> " + nurseModel.getFirstname() + " " + nurseModel.getLastname() + "\n";
                    }

                }
                else{
                    output="No Professor available yet.\nPlease register new Professor.";
                }

                textViewDisplay.setText(output);
            }
        });
    }

    /** Called when the user taps the Send button */
    public void openNurseLoginPage(View view) {
        Intent intent = new Intent(this, NurseLoginActivity.class);
        startActivity(intent);
    }
}