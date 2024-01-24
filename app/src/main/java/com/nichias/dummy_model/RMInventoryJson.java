package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RMInventoryJson {
    @SerializedName("Coil_No")
    @Expose
    private String Coil_No;
    @SerializedName("Size")
    @Expose
    private String Size;
    @SerializedName("Net_Weight")
    @Expose
    private String Net_Weight;
    @SerializedName("Type")
    @Expose
    private String Type;

    public String getCoil_No() {
        return Coil_No;
    }

    public void setCoil_No(String coil_No) {
        Coil_No = coil_No;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getNet_Weight() {
        return Net_Weight;
    }

    public void setNet_Weight(String net_Weight) {
        Net_Weight = net_Weight;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
