package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EarningModel {
    @SerializedName("mobno")
    @Expose
    private String mobno;

    @SerializedName("month")
    @Expose
    private String month;

    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("basicval")
    @Expose
    private String basicval;

    @SerializedName("da")
    @Expose
    private String da;

    @SerializedName("hra")
    @Expose
    private String hra;

    @SerializedName("risk")
    @Expose
    private String risk;

    @SerializedName("travel")
    @Expose
    private String travel;

    @SerializedName("wash")
    @Expose
    private String wash;

    @SerializedName("other_1")
    @Expose
    private String other_1;

    @SerializedName("other_2")
    @Expose
    private String other_2;

    @SerializedName("other_3")
    @Expose
    private String other_3;

    @SerializedName("total")
    @Expose
    private String total;


    public String getMobno() {
        return mobno;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getBasicval() {
        return basicval;
    }

    public String getDa() {
        return da;
    }

    public String getHra() {
        return hra;
    }

    public String getRisk() {
        return risk;
    }

    public String getTravel() {
        return travel;
    }

    public String getWash() {
        return wash;
    }

    public String getOther_1() {
        return other_1;
    }

    public String getOther_2() {
        return other_2;
    }

    public String getOther_3() {
        return other_3;
    }

    public String getTotal() {
        return total;
    }
}
