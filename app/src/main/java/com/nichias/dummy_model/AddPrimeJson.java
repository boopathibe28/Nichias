package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPrimeJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("schedule_num")
    @Expose
    private String schedule_num;
    @SerializedName("wo_num")
    @Expose
    private String wo_num;
    @SerializedName("ssipl")
    @Expose
    private String ssipl;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("thick")
    @Expose
    private String thick;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("pcs")
    @Expose
    private String pcs;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSchedule_num() {
        return schedule_num;
    }

    public void setSchedule_num(String schedule_num) {
        this.schedule_num = schedule_num;
    }

    public String getWo_num() {
        return wo_num;
    }

    public void setWo_num(String wo_num) {
        this.wo_num = wo_num;
    }

    public String getSsipl() {
        return ssipl;
    }

    public void setSsipl(String ssipl) {
        this.ssipl = ssipl;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getThick() {
        return thick;
    }

    public void setThick(String thick) {
        this.thick = thick;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }
}
