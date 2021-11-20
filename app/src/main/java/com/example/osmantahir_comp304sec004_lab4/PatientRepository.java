package com.example.osmantahir_comp304sec004_lab4;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PatientRepository
{
    private final PatientDao patientDao;
    private MutableLiveData<Integer> addpatient=new MutableLiveData<>();
    private LiveData<List<PatientModel>> patientlist;
    private MutableLiveData<Integer> updatePatientResult = new MutableLiveData<>();


    public PatientRepository(Context context, int nid)
    {
        AppDatabase db=AppDatabase.getInstance(context);
        patientDao=db.PatientDao();
        patientlist=patientDao.getPatientData(nid);

    }
    public void Addapatient(final PatientModel patientModel)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.AddPatient(patientModel);
                    addpatient.postValue(1);
                }
                catch (Exception e)
                {
                    addpatient.postValue(0);
                }
            }
        }).start();
    }
    public LiveData<Integer> AddPatientResult(){
        return addpatient;
    }
    public LiveData<List<PatientModel>> getPatientlist()
    {
        return patientlist;
    }
    public void UpdatePatientInfo(final PatientModel person) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    patientDao.Updatepatient(person);
                    updatePatientResult.postValue(1);
                } catch (Exception e) {
                    updatePatientResult.postValue(0);
                }
            }
        }).start();
    }
    public LiveData<Integer> getUpdateResult() {
        return updatePatientResult;
    }
}