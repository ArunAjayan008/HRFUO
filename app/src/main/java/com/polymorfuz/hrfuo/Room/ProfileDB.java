package com.polymorfuz.hrfuo.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
