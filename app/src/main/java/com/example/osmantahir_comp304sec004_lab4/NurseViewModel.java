package com.example.osmantahir_comp304sec004_lab4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NurseViewModel extends AndroidViewModel
{
    private NurseRepository nurseRepository;
    private LiveData<Integer> insertresult;
    private LiveData<List<NurseModel>>getnurse;
    private LiveData<NurseModel> LoginRes;

    //private NurseModel LoginRes=new NurseModel();
    public NurseViewModel(@NonNull Application application) {
        super(application);
        nurseRepository=new NurseRepository(application);
        insertresult=nurseRepository.getInsertResult();
        getnurse=nurseRepository.getNurselist();
        LoginRes=nurseRepository.getLoginRes();

    }
    public void insert(NurseModel nurseModel)
    {
        nurseRepository.insert(nurseModel);
    }
    public LiveData<Integer> getInsertresult()
    {
        return insertresult;
    }
    public LiveData<List<NurseModel>> getNurselist()
    {
        return getnurse;
    }

    public void getLogin(int nurseid, String password)
    {
        nurseRepository.getLogin(nurseid,password);
    }
    public LiveData<NurseModel> getLoginRes(){
        return LoginRes;
    }

}