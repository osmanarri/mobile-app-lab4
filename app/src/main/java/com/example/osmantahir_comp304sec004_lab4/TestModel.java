package com.example.osmantahir_comp304sec004_lab4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TestModel
{
    @PrimaryKey(autoGenerate = true)
    private int testid;
    private int patientid,nurseid;
    private String BPL,BHP,temperature;

    public int getTestid() {
        return testid;
    }

    public void setTestid(int testid) {
        this.testid = testid;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getNurseid() {
        return nurseid;
    }

    public void setNurseid(int nurseid) {
        this.nurseid = nurseid;
    }

    public String getBPL() {
        return BPL;
    }

    public void setBPL(String BPL) {
        this.BPL = BPL;
    }

    public String getBHP() {
        return BHP;
    }

    public void setBHP(String BHP) {
        this.BHP = BHP;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}