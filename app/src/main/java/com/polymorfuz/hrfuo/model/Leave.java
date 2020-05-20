package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leave {
    @SerializedName("CL")
    @Expose
    private String CL;
    @SerializedName("EL")
    @Expose
    private String EL;
    @SerializedName("HPL")
    @Expose
    private String HPL;
    @SerializedName("ESI")
    @Expose
    private String ESI;
    @SerializedName("Absent")
    @Expose
    private String Absent;

    public String getCL() {
        return CL;
    }

    public String getEL() {
        return EL;
    }

    public String getHPL() {
        return HPL;
    }

    public String getESI() {
        return ESI;
    }

    public String getAbsent() {
        return Absent;
    }
}
