package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SecondaryPackingJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("ProductLotNo")
    @Expose
    private String ProductLotNo;
    @SerializedName("InwardArray")
    @Expose
    private List<InwardArray> inwardArray;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProductLotNo() {
        return ProductLotNo;
    }

    public void setProductLotNo(String productLotNo) {
        ProductLotNo = productLotNo;
    }

    public List<InwardArray> getInwardArray() {
        return inwardArray;
    }

    public void setInwardArray(List<InwardArray> inwardArray) {
        this.inwardArray = inwardArray;
    }

    public static class InwardArray {
        @SerializedName("SerialCode")
        @Expose
        private String SerialCode;
        @SerializedName("Sl_No")
        @Expose
        private String Sl_No;
        @SerializedName("PartNo")
        @Expose
        private String PartNo;

        public String getSerialCode() {
            return SerialCode;
        }

        public void setSerialCode(String serialCode) {
            SerialCode = serialCode;
        }

        public String getSl_No() {
            return Sl_No;
        }

        public void setSl_No(String sl_No) {
            Sl_No = sl_No;
        }

        public String getPartNo() {
            return PartNo;
        }

        public void setPartNo(String partNo) {
            PartNo = partNo;
        }
    }
}