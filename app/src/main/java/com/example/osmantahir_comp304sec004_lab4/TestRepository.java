package com.example.osmantahir_comp304sec004_lab4;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TestRepository
{
    private final TestDao testDao;
    private LiveData<List<PatientModel>> patientList;
    private MutableLiveData<Integer> addtest=new MutableLiveData<>();

    public TestRepository(Context context)
    {
        AppDatabase db = AppDatabase.getInstance(context);
        testDao = db.testDao();
        patientList=testDao.getPatientData(context.getSharedPreferences("pref",Context.MODE_PRIVATE).getInt("nid",0));
    }

    public  LiveData<List<PatientModel>> getAllPatient() {
        return patientList;
    }

    public void Addtest(final TestModel testModel)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testDao.AddTest(testModel);
                    addtest.postValue(1);
                }
                catch (Exception e)
                {
                    addtest.postValue(0);
                }
            }
        }).start();
    }
    public LiveData<Integer> AddTestResult(){
        return addtest;
    }

    public LiveData<List<TestModel>> getPatientTestData(int pid, int nid)
    {
        return testDao.getPatientTestData(pid,nid);
    }


}
