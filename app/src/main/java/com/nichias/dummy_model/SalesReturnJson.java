package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesReturnJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("ssipl")
    @Expose
    private String ssipl;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSsipl() {
        return ssipl;
    }

    public void setSsipl(String ssipl) {
        this.ssipl = ssipl;
    }

}
