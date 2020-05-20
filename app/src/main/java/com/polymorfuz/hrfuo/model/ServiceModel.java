package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceModel {
    @SerializedName("Doj")
    @Expose
    private String doj;

    @SerializedName("Desig on join")
    @Expose
    private String desig_onjoin;

    @SerializedName("Current desig")
    @Expose
    private String curnt_desig;

    @SerializedName("Dor")
    @Expose
    private String dor;

    @SerializedName("Date of last prom")
    @Expose
    private String last_prom;

    @SerializedName("Next promotion")
    @Expose
    private String next_prom;

    public String getDoj() {
        return doj;
    }

    public String getDesig_onjoin() {
        return desig_onjoin;
    }

    public String getCurnt_desig() {
        return curnt_desig;
    }

    public String getDor() {
        return dor;
    }

    public String getLast_prom() {
        return last_prom;
    }

    public String getNext_prom() {
        return next_prom;
    }
}
