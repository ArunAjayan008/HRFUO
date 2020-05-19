package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("mobno")
    @Expose
    private String mobno;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("edu_qual")
    @Expose
    private String edu_qual;
    @SerializedName("address")
    @Expose
    private String address;

    public String getMobno() {
        return mobno;
    }

    public String getAge() {
        return age;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getEdu_qual() {
        return edu_qual;
    }

    public String getAddress() {
        return address;
    }
}