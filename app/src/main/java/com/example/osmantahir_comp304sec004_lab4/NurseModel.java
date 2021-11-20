package com.example.osmantahir_comp304sec004_lab4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NurseModel
{
    @PrimaryKey(autoGenerate = true)
    private int nurseid;
    private String firstname,lastname,department,password;

    public int getNurseid() {
        return nurseid;
    }
    public void setNurseid(int nurseid) {
        this.nurseid = nurseid;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
