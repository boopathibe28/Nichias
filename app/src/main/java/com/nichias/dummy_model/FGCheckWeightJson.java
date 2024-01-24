package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FGCheckWeightJson {
    @SerializedName("Method")
    @Expose
    private String Method;
    @SerializedName("SSIPL")
    @Expose
    private String SSIPL;

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getSSIPL() {
        return SSIPL;
    }

    public void setSSIPL(String SSIPL) {
        this.SSIPL = SSIPL;
    }
}
