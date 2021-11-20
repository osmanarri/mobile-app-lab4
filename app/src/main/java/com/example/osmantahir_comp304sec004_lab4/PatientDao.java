package com.example.osmantahir_comp304sec004_lab4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao
{
    @Insert
    void AddPatient(PatientModel pm);

    @Query("Select * from patientmodel where nurseid=:nid")
    LiveData<List<PatientModel>> getPatientData(int nid);

    @Update
    void Updatepatient(PatientModel pm);
}
