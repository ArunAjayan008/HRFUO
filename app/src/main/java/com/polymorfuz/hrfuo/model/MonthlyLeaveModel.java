package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthlyLeaveModel {
    @SerializedName("userid")
    @Expose
    private int userid;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("leave_type")
    @Expose
    private String leave_type;

    public int getUserid() {
        return userid;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getLeave_type() {
        return leave_type;
    }
}
