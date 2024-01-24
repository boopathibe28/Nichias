package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InwardArrayResponse {
    @SerializedName("InwardArray")
    @Expose
    private List<InwardArray> inwardArray;

    public List<InwardArray> getInwardArray() {
        return inwardArray;
    }

    public void setInwardArray(List<InwardArray> inwardArray) {
        this.inwardArray = inwardArray;
    }

    public static class InwardArray {
        @SerializedName("Customer")
        @Expose
        private String Customer;
        @SerializedName("PartName")
        @Expose
        private String PartName;
        @SerializedName("PartNo")
        @Expose
        private String PartNo;
        @SerializedName("SerialCode")
        @Expose
        private String SerialCode;


        public String getCustomer() {
            return Customer;
        }

        public void setCustomer(String customer) {
            Customer = customer;
        }

        public String getPartName() {
            return PartName;
        }

        public void setPartName(String partName) {
            PartName = partName;
        }

        public String getPartNo() {
            return PartNo;
        }

        public void setPartNo(String partNo) {
            PartNo = partNo;
        }

        public String getSerialCode() {
            return SerialCode;
        }

        public void setSerialCode(String serialCode) {
            SerialCode = serialCode;
        }
    }

}
