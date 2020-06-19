package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPF_ESIModel {
    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("epfacno")
    @Expose
    private String epfacno;

    @SerializedName("uan")
    @Expose
    private String uan;

    @SerializedName("epf_passwd")
    @Expose
    private String epf_passwd;

    @SerializedName("esi_no")
    @Expose
    private String esi_no;

    @SerializedName("esi_empno")
    @Expose
    private String esi_empno;


    public String getUserid() {
        return userid;
    }

    public String getEpfacno() {
        return epfacno;
    }

    public String getUan() {
        return uan;
    }

    public String getEpf_passwd() {
        return epf_passwd;
    }

    public String getEsi_no() {
        return esi_no;
    }

    public String getEsi_empno() {
        return esi_empno;
    }
}
