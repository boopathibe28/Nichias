package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RMLocationEnteryJson {
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
    @SerializedName("RMRackNo")
    @Expose
    private String RMRackNo;
    @SerializedName("RMLocation")
    @Expose
    private String RMLocation;
    @SerializedName("SerialCode")
    @Expose
    private String SerialCode;
    @SerializedName("QuantityMeter")
    @Expose
    private String QuantityMeter;

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

    public String getRMLocation() {
        return RMLocation;
    }

    public void setRMLocation(String RMLocation) {
        this.RMLocation = RMLocation;
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

    public String getRMRackNo() {
        return RMRackNo;
    }

    public void setRMRackNo(String RMRackNo) {
        this.RMRackNo = RMRackNo;
    }

    public String getSerialCode() {
        return SerialCode;
    }

    public void setSerialCode(String serialCode) {
        SerialCode = serialCode;
    }

    public String getQuantityMeter() {
        return QuantityMeter;
    }

    public void setQuantityMeter(String quantityMeter) {
        QuantityMeter = quantityMeter;
    }
}
