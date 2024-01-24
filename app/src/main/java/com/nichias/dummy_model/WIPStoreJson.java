package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WIPStoreJson {
    @SerializedName("SSIPL_Id")
    @Expose
    private String SSIPL_Id;
    @SerializedName("Location_Id")
    @Expose
    private String Location_Id;
    @SerializedName("Grid_No")
    @Expose
    private String Grid_No;
    @SerializedName("Grid_Sub_No")
    @Expose
    private String Grid_Sub_No;
    @SerializedName("Type")
    @Expose
    private String Type;

    public String getSSIPL_Id() {
        return SSIPL_Id;
    }

    public void setSSIPL_Id(String SSIPL_Id) {
        this.SSIPL_Id = SSIPL_Id;
    }

    public String getLocation_Id() {
        return Location_Id;
    }

    public void setLocation_Id(String location_Id) {
        Location_Id = location_Id;
    }

    public String getGrid_No() {
        return Grid_No;
    }

    public void setGrid_No(String grid_No) {
        Grid_No = grid_No;
    }

    public String getGrid_Sub_No() {
        return Grid_Sub_No;
    }

    public void setGrid_Sub_No(String grid_Sub_No) {
        Grid_Sub_No = grid_Sub_No;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
