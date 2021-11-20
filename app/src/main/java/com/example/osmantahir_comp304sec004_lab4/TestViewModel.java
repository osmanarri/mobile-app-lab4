package com.example.osmantahir_comp304sec004_lab4;
import  android.app.Application;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TestViewModel extends AndroidViewModel
{
    private TestRepository testRepository;
    private LiveData<List<PatientModel>> patientlist;
    private LiveData<Integer> testresult;

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository=new TestRepository(application);
        patientlist=testRepository.getAllPatient();
        testresult=testRepository.AddTestResult();
    }

    public LiveData<List<PatientModel>> getAllPatientlist() { return patientlist; }

    public void inserttest(TestModel testModel)
    {
        testRepository.Addtest(testModel);
    }
    public LiveData<Integer> gettestresult()
    {
        return testresult;
    }
    public LiveData<List<TestModel>> getpateintTestData(int pid, int nid)
    {
        return testRepository.getPatientTestData(pid,nid);
    }
}
