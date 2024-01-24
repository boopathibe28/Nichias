package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorSwap {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("MachineNo")
    @Expose
    private String MachineNo;
    @SerializedName("OperatorId")
    @Expose
    private String OperatorId;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMachineNo() {
        return MachineNo;
    }

    public void setMachineNo(String machineNo) {
        MachineNo = machineNo;
    }

    public String getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(String operatorId) {
        OperatorId = operatorId;
    }
}
