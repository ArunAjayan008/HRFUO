package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notifications {
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("Desc")
    @Expose
    private String Desc;
    @SerializedName("Date_time")
    @Expose
    private String Date_time;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("Target_date")
    @Expose
    private String Target_date;

    public Notifications(String title, String desc, String date_time, String userid, String target_date) {
        Title = title;
        Desc = desc;
        Date_time = date_time;
        this.userid = userid;
        Target_date = target_date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDate_time() {
        return Date_time;
    }

    public void setDate_time(String date_time) {
        Date_time = date_time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTarget_date() {
        return Target_date;
    }

    public void setTarget_date(String target_date) {
        Target_date = target_date;
    }
}
