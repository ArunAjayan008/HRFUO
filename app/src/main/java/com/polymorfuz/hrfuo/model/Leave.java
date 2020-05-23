package com.polymorfuz.hrfuo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leave {
    @SerializedName("cl")
    @Expose
    private int cl;
    @SerializedName("el")
    @Expose
    private int el;
    @SerializedName("hpl")
    @Expose
    private int hpl;
    @SerializedName("esi")
    @Expose
    private int esi;
    @SerializedName("absent")
    @Expose
    private int absent;

    public int getCl() {
        return cl;
    }

    public int getEl() {
        return el;
    }

    public int getHpl() {
        return hpl;
    }

    public int getEsi() {
        return esi;
    }

    public int getAbsent() {
        return absent;
    }
}
