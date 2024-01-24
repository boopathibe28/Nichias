package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BusinessTypeListApiResponse {

    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("status")
    @Expose
    private String status;


    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public class Result {

        @SerializedName("business_types")
        @Expose
        private List<BusinessType> businessTypes;

        public List<BusinessType> getBusinessTypes() {
            return businessTypes;
        }

        public void setBusinessTypes(List<BusinessType> businessTypes) {
            this.businessTypes = businessTypes;
        }

    }

    public class BusinessType {

        @SerializedName("types")
        @Expose
        private String types;

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

    }

}
