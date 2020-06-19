package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthlyLeaveModel {
    @SerializedName("userid")
    @Expose
    private int userid;
    @SerializedName("date")
    @Expose
    private int date;
    @SerializedName("month")
    @Expose
    private int month;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("leave_type")
    @Expose
    private int leave_type;

    public MonthlyLeaveModel(int userid, int date, int month, int year, int leave_type) {
        this.userid = userid;
        this.date = date;
        this.month = month;
        this.year = year;
        this.leave_type = leave_type;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(int leave_type) {
        this.leave_type = leave_type;
    }
}
