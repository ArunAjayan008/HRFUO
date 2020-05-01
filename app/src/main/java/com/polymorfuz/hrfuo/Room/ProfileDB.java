package com.polymorfuz.hrfuo.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "profile_db")
public class ProfileDB {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="empname")
    private String empname;

    @ColumnInfo(name="age")
    private String age;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name="qualification")
    private String qualification;

    @ColumnInfo(name = "designation")
    private String designation;

    @ColumnInfo(name = "dob")
    private Date dob;

    @ColumnInfo(name = "address")
    private String address;

    public ProfileDB(@NonNull String empname, String age, String gender, String qualification, String designation, Date dob, String address) {
        this.empname = empname;
        this.age = age;
        this.gender = gender;
        this.qualification = qualification;
        this.designation = designation;
        this.dob = dob;
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @NonNull
    public String getEmpname() {
        return empname;
    }

    public void setEmpname(@NonNull String empname) {
        this.empname = empname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
