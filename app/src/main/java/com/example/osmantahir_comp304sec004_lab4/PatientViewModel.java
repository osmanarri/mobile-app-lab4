package com.example.osmantahir_comp304sec004_lab4;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository patientRepository;
    private LiveData<Integer> patientresult;
    private LiveData<List<PatientModel>> patientlist;
    private LiveData<Integer> updateResult;
    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository=new PatientRepository(application,application.getSharedPreferences("pref", Context.MODE_PRIVATE).getInt("nid",0));
        patientresult=patientRepository.AddPatientResult();
        patientlist=patientRepository.getPatientlist();
        updateResult=patientRepository.getUpdateResult();


    }
    public void insertpatient(PatientModel patientModel)
    {
        patientRepository.Addapatient(patientModel);
    }
    public LiveData<Integer> getpatientresult()
    {
        return patientresult;
    }

    public LiveData<List<PatientModel>> getAllPatientlist() { return patientlist; }

    public void Update(PatientModel person) {
        patientRepository.UpdatePatientInfo(person);
    }
    public LiveData<Integer> UpdatepatientInfo()
    {
        return updateResult;
    }
}
