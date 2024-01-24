package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokayokeCheck {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("RollNo")
    @Expose
    private String RollNo;
    @SerializedName("LotNo")
    @Expose
    private String LotNo;
    @SerializedName("Size")
    @Expose
    private String Size;
    @SerializedName("ManagedNo")
    @Expose
    private String ManagedNo;
    @SerializedName("MachineNo")
    @Expose
    private String MachineNo;
    @SerializedName("Mould")
    @Expose
    private String Mould;
    @SerializedName("OperatorId")
    @Expose
    private String OperatorId;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String lotNo) {
        LotNo = lotNo;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getManagedNo() {
        return ManagedNo;
    }

    public void setManagedNo(String managedNo) {
        ManagedNo = managedNo;
    }

    public String getMachineNo() {
        return MachineNo;
    }

    public void setMachineNo(String machineNo) {
        MachineNo = machineNo;
    }

    public String getMould() {
        return Mould;
    }

    public void setMould(String mould) {
        Mould = mould;
    }

    public String getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(String operatorId) {
        OperatorId = operatorId;
    }
}
