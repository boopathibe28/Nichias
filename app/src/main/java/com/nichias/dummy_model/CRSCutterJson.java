package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CRSCutterJson {
    @SerializedName("Machine")
    @Expose
    private String Machine;
    @SerializedName("SSIPL")
    @Expose
    private String SSIPL;
    @SerializedName("Wo_Num")
    @Expose
    private String Wo_Num;

    public String getMachine() {
        return Machine;
    }

    public void setMachine(String machine) {
        Machine = machine;
    }

    public String getSSIPL() {
        return SSIPL;
    }

    public void setSSIPL(String SSIPL) {
        this.SSIPL = SSIPL;
    }

    public String getWo_Num() {
        return Wo_Num;
    }

    public void setWo_Num(String wo_Num) {
        Wo_Num = wo_Num;
    }
}
