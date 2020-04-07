package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Age")
    @Expose
    private String Age;
    @SerializedName("Gender")
    @Expose
    private String Gender;
    @SerializedName("Qualify")
    @Expose
    private String Qualify;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getQualify() {
        return Qualify;
    }

    public void setQualify(String qualify) {
        Qualify = qualify;
    }
}
