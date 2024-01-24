package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("wo_number")
    @Expose
    private String wo_number;
    @SerializedName("opbatch")
    @Expose
    private String opbatch;
    @SerializedName("weight")
    @Expose
    private String weight;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getWo_number() {
        return wo_number;
    }

    public void setWo_number(String wo_number) {
        this.wo_number = wo_number;
    }

    public String getOpbatch() {
        return opbatch;
    }

    public void setOpbatch(String opbatch) {
        this.opbatch = opbatch;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
