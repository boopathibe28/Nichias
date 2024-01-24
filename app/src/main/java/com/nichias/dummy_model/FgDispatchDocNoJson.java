package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FgDispatchDocNoJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("docno")
    @Expose
    private String docno;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }
}
