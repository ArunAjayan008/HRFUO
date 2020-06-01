package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolidayModel {
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("desc")
    @Expose
    private String desc;

    public HolidayModel(String date, String desc) {
        this.date = date;
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
