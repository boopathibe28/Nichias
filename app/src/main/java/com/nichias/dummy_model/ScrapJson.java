package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

public class ScrapJson {
    private String method;
    private JSONArray ssipl;
    private JSONArray weight;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public JSONArray getSsipl() {
        return ssipl;
    }

    public void setSsipl(JSONArray ssipl) {
        this.ssipl = ssipl;
    }

    public JSONArray getWeight() {
        return weight;
    }

    public void setWeight(JSONArray weight) {
        this.weight = weight;
    }
}
