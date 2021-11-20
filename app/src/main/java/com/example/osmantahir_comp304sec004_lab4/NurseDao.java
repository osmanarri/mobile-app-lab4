package com.example.osmantahir_comp304sec004_lab4;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NurseDao {
    //
    @Insert
    void insert(NurseModel nurse);

    @Query("select * from NurseModel")
    LiveData<List<NurseModel>> getNurse();

    @Query("select * from NurseModel where nurseid=:nurseid and password=:password")
    NurseModel getNursedata(int nurseid,String password);
}

