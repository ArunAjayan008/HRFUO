package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPFModel {
    @SerializedName("mobno")
    @Expose
    private String mobno;

    @SerializedName("uan")
    @Expose
    private String uan;

    @SerializedName("password")
    @Expose
    private String password;


    public String getMobno() {
        return mobno;
    }

    public String getUan() {
        return uan;
    }

    public String getPassword() {
        return password;
    }
}
