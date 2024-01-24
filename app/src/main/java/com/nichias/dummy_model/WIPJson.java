package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WIPJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("op_batch")
    @Expose
    private String op_batch;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOp_batch() {
        return op_batch;
    }

    public void setOp_batch(String op_batch) {
        this.op_batch = op_batch;
    }
}
