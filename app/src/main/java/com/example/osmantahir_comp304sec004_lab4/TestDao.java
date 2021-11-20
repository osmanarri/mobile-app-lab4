package com.example.osmantahir_comp304sec004_lab4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TestDao
{
    @Insert
    void AddTest(TestModel tm);

    @Query("Select * from patientmodel where nurseid=:nid")
    LiveData<List<PatientModel>> getPatientData(int nid);

    @Query("Select * from Testmodel where patientid=:pid and nurseid=:nid")
    LiveData<List<TestModel>> getPatientTestData(int pid,int nid);
}
