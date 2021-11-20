package com.example.osmantahir_comp304sec004_lab4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NurseModel.class, PatientModel.class, TestModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "hospitalDB";
    public abstract NurseDao nurseDao();
    public abstract PatientDao PatientDao();
    public abstract TestDao testDao();
    //
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}