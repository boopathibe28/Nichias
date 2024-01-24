package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FGJson {
    @SerializedName("SSIPL")
    @Expose
    private String SSIPL;
    @SerializedName("Thick")
    @Expose
    private String Thick;
    @SerializedName("Width")
    @Expose
    private String Width;
    @SerializedName("Length")
    @Expose
    private String Length;
    @SerializedName("Weight")
    @Expose
    private String Weight;
    @SerializedName("Method")
    @Expose
    private String Method;

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getSSIPL() {
        return SSIPL;
    }

    public void setSSIPL(String SSIPL) {
        this.SSIPL = SSIPL;
    }

    public String getThick() {
        return Thick;
    }

    public void setThick(String thick) {
        Thick = thick;
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }
}
