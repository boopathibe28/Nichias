package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RollEndCut {
    @SerializedName("wo_num")
    @Expose
    private String wo_num;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("schedule_num")
    @Expose
    private String schedule_num;
    @SerializedName("ssipl")
    @Expose
    private String ssipl;

    public String getSsipl() {
        return ssipl;
    }

    public void setSsipl(String ssipl) {
        this.ssipl = ssipl;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
