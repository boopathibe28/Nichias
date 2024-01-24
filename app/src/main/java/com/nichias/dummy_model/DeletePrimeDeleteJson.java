package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeletePrimeDeleteJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("opbatch")
    @Expose
    private String opbatch;
    @SerializedName("wo_num")
    @Expose
    private String wo_num;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOpbatch() {
        return opbatch;
    }

    public void setOpbatch(String opbatch) {
        this.opbatch = opbatch;
    }

    public String getWo_num() {
        return wo_num;
    }

    public void setWo_num(String wo_num) {
        this.wo_num = wo_num;
    }
}
