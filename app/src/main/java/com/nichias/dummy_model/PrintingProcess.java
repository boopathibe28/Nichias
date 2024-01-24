package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrintingProcess {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("batch")
    @Expose
    private String batch;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("docno")
    @Expose
    private String docno;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }
}
