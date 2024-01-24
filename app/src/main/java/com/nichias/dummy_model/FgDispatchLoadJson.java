package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FgDispatchLoadJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("docno")
    @Expose
    private String docno;
    @SerializedName("values")
    @Expose
    private List<Value> values;

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

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }


    public static class Value {

        @SerializedName("batch")
        @Expose
        private String batch;
        @SerializedName("barcode_batch")
        @Expose
        private String barcode_batch;
        @SerializedName("barcode_qty")
        @Expose
        private String barcode_qty;

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getBarcode_batch() {
            return barcode_batch;
        }

        public void setBarcode_batch(String barcode_batch) {
            this.barcode_batch = barcode_batch;
        }

        public String getBarcode_qty() {
            return barcode_qty;
        }

        public void setBarcode_qty(String barcode_qty) {
            this.barcode_qty = barcode_qty;
        }
    }
}
