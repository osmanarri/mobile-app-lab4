package com.example.osmantahir_comp304sec004_lab4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatientModel implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int patientid;
    private String firstname,lastname,department;
    int nurseid,room;


    public PatientModel(){}


    protected PatientModel(Parcel in) {
        patientid = in.readInt();
        firstname = in.readString();
        lastname = in.readString();
        department = in.readString();
        nurseid = in.readInt();
        room = in.readInt();
    }

    public static final Creator<PatientModel> CREATOR = new Creator<PatientModel>() {
        @Override
        public PatientModel createFromParcel(Parcel in) {
            return new PatientModel(in);
        }

        @Override
        public PatientModel[] newArray(int size) {
            return new PatientModel[size];
        }
    };

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
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

    public int getNurseid() {
        return nurseid;
    }

    public void setNurseid(int nurseid) {
        this.nurseid = nurseid;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(patientid);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(department);
        dest.writeInt(nurseid);
        dest.writeInt(room);
    }
}
