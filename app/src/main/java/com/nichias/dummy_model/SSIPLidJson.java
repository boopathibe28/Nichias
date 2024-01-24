package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SSIPLidJson {
    @SerializedName("SSIPL_Id")
    @Expose
    private String SSIPL_Id;
    @SerializedName("Coil_No")
    @Expose
    private String Coil_No;

    public String getSSIPL_Id() {
        return SSIPL_Id;
    }

    public void setSSIPL_Id(String SSIPL_Id) {
        this.SSIPL_Id = SSIPL_Id;
    }

    public String getCoil_No() {
        return Coil_No;
    }

    public void setCoil_No(String coil_No) {
        Coil_No = coil_No;
    }
}
