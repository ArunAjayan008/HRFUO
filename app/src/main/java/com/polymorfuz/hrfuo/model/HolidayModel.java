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

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }
}
