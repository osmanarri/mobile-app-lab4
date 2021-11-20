package com.example.osmantahir_comp304sec004_lab4;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class NurseRepository
{
    private final NurseDao nurseDao;
    private MutableLiveData<Integer> insertnurse=new MutableLiveData<>();
    private LiveData<List<NurseModel>> nurselist;
    private MutableLiveData<NurseModel> nursedata=new MutableLiveData<>();


    public NurseRepository(Context context)
    {
        AppDatabase db=AppDatabase.getInstance(context);
        nurseDao=db.nurseDao();
        nurselist=nurseDao.getNurse();

    }
    public LiveData<List<NurseModel>> getNurselist() {
        return nurselist;
    }
    public void insert(NurseModel nurseModel) {
        insertAsync(nurseModel);
    }
    public void getLogin(final int nurseid, final String password)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NurseModel abc = nurseDao.getNursedata(nurseid, password);
                    nursedata.postValue(abc);
                }
                catch (Exception e)
                {
                    nursedata.postValue(null);
                }
            }
        }).start();
    }
    public LiveData<NurseModel> getLoginRes(){
        return nursedata;
    }
    public LiveData<Integer>getInsertResult() {
        return insertnurse;
    }
    private void insertAsync(final NurseModel nurseModel)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nurseDao.insert(nurseModel);
                    insertnurse.postValue(1);
                }
                catch (Exception e)
                {
                    insertnurse.postValue(0);
                }
            }
        }).start();
    }
}