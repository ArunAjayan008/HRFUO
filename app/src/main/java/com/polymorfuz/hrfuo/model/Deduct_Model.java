package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deduct_Model {
    @SerializedName("mobno")
    @Expose
    private String mobno;

    @SerializedName("month")
    @Expose
    private String month;

    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("pf")
    @Expose
    private String pf;

    @SerializedName("esi")
    @Expose
    private String esi;

    @SerializedName("f_adv")
    @Expose
    private String f_adv;

    @SerializedName("sifl")
    @Expose
    private String sifl;

    @SerializedName("canteen")
    @Expose
    private String canteen;

    @SerializedName("other1")
    @Expose
    private String other1;

    @SerializedName("other2")
    @Expose
    private String other2;

    @SerializedName("other3")
    @Expose
    private String other3;

    @SerializedName("other4")
    @Expose
    private String other4;

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

    public String getPf() {
        return pf;
    }

    public String getEsi() {
        return esi;
    }

    public String getF_adv() {
        return f_adv;
    }

    public String getSifl() {
        return sifl;
    }

    public String getCanteen() {
        return canteen;
    }

    public String getOther1() {
        return other1;
    }

    public String getOther2() {
        return other2;
    }

    public String getOther3() {
        return other3;
    }

    public String getOther4() {
        return other4;
    }

    public String getTotal() {
        return total;
    }
}
