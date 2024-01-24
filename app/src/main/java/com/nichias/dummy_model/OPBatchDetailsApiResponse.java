package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OPBatchDetailsApiResponse {
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("code")
    @Expose
    private Integer code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public class Result {
        @SerializedName("Gross Weight")
        @Expose
        private String Gross_Weight;
        @SerializedName("Customer Code")
        @Expose
        private String Customer_Code;
        @SerializedName("Length")
        @Expose
        private String Length;
        @SerializedName("Thick")
        @Expose
        private String Thick;
        @SerializedName("Width")
        @Expose
        private String Width;
        @SerializedName("Customer Name")
        @Expose
        private String Customer_Name;


        public String getGross_Weight() {
            return Gross_Weight;
        }

        public void setGross_Weight(String gross_Weight) {
            Gross_Weight = gross_Weight;
        }

        public String getCustomer_Code() {
            return Customer_Code;
        }

        public void setCustomer_Code(String customer_Code) {
            Customer_Code = customer_Code;
        }

        public String getLength() {
            return Length;
        }

        public void setLength(String length) {
            Length = length;
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

        public String getCustomer_Name() {
            return Customer_Name;
        }

        public void setCustomer_Name(String customer_Name) {
            Customer_Name = customer_Name;
        }
    }

}
