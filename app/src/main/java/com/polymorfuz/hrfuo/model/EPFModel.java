package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPFModel {
    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("uan")
    @Expose
    private String uan;

    @SerializedName("password")
    @Expose
    private String password;


    public String getUserid() {
        return userid;
    }

    public String getUan() {
        return uan;
    }

    public String getPassword() {
        return password;
    }
}
