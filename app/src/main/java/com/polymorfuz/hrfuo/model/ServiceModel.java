package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceModel {

    @SerializedName("mobno")
    @Expose
    private String mobno;

    @SerializedName("doj")
    @Expose
    private String doj;

    @SerializedName("desig_on_join")
    @Expose
    private String desig_on_join;

    @SerializedName("current_desig")
    @Expose
    private String current_desig;

    @SerializedName("dor")
    @Expose
    private String dor;

    @SerializedName("date_of_lastprom")
    @Expose
    private String date_of_lastprom;

    @SerializedName("next_promotion")
    @Expose
    private String next_promotion;

    public String getMobno() {
        return mobno;
    }

    public String getDoj() {
        return doj;
    }

    public String getDesig_on_join() {
        return desig_on_join;
    }

    public String getCurrent_desig() {
        return current_desig;
    }

    public String getDor() {
        return dor;
    }

    public String getDate_of_lastprom() {
        return date_of_lastprom;
    }

    public String getNext_promotion() {
        return next_promotion;
    }
}
