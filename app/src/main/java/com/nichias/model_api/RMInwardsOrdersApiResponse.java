package com.nichias.model_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RMInwardsOrdersApiResponse {
    @SerializedName("data")
    @Expose
    private List<Data> data;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Data {
        @SerializedName("RollNo")
        @Expose
        private String RollNo;
        @SerializedName("InwardDate")
        @Expose
        private String InwardDate;
        @SerializedName("Quality")
        @Expose
        private String Quality;

        public String getRollNo() {
            return RollNo;
        }

        public void setRollNo(String rollNo) {
            RollNo = rollNo;
        }

        public String getInwardDate() {
            return InwardDate;
        }

        public void setInwardDate(String inwardDate) {
            InwardDate = inwardDate;
        }

        public String getQuality() {
            return Quality;
        }

        public void setQuality(String quality) {
            Quality = quality;
        }
    }
}
