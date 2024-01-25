package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InspectionCheckJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("SERIAL_CODE")
    @Expose
    private String SERIAL_CODE;
    @SerializedName("SL_NO")
    @Expose
    private String SL_NO;
    @SerializedName("PART_NO")
    @Expose
    private String PART_NO;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSERIAL_CODE() {
        return SERIAL_CODE;
    }

    public void setSERIAL_CODE(String SERIAL_CODE) {
        this.SERIAL_CODE = SERIAL_CODE;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getPART_NO() {
        return PART_NO;
    }

    public void setPART_NO(String PART_NO) {
        this.PART_NO = PART_NO;
    }
}
