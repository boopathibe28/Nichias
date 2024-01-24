package com.nichias.model_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GRNListApiResponse {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("status")
    @Expose
    private String status;


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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Datum {
        @SerializedName("ReceivedDate")
        @Expose
        private String ReceivedDate;
        @SerializedName("InwardArray")
        @Expose
        private List<InwardArray> inwardArray;
        @SerializedName("InwardDate")
        @Expose
        private String InwardDate;
        @SerializedName("Remarks")
        @Expose
        private String Remarks;
        @SerializedName("Quantity")
        @Expose
        private String Quantity;
        @SerializedName("Supplier")
        @Expose
        private String Supplier;
        @SerializedName("MaterialName")
        @Expose
        private String MaterialName;
        @SerializedName("MaterialGrade")
        @Expose
        private String MaterialGrade;


        public String getReceivedDate() {
            return ReceivedDate;
        }

        public void setReceivedDate(String receivedDate) {
            ReceivedDate = receivedDate;
        }

        public List<InwardArray> getInwardArray() {
            return inwardArray;
        }

        public void setInwardArray(List<InwardArray> inwardArray) {
            this.inwardArray = inwardArray;
        }

        public String getInwardDate() {
            return InwardDate;
        }

        public void setInwardDate(String inwardDate) {
            InwardDate = inwardDate;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String remarks) {
            Remarks = remarks;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String quantity) {
            Quantity = quantity;
        }

        public String getSupplier() {
            return Supplier;
        }

        public void setSupplier(String supplier) {
            Supplier = supplier;
        }

        public String getMaterialName() {
            return MaterialName;
        }

        public void setMaterialName(String materialName) {
            MaterialName = materialName;
        }

        public String getMaterialGrade() {
            return MaterialGrade;
        }

        public void setMaterialGrade(String materialGrade) {
            MaterialGrade = materialGrade;
        }
    }
    public class InwardArray {
        @SerializedName("PartName")
        @Expose
        private String PartName;
        @SerializedName("PartNo")
        @Expose
        private String PartNo;
        @SerializedName("Customer")
        @Expose
        private String Customer;
        @SerializedName("SerialCode")
        @Expose
        private String SerialCode;

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

        public String getCustomer() {
            return Customer;
        }

        public void setCustomer(String customer) {
            Customer = customer;
        }

        public String getSerialCode() {
            return SerialCode;
        }

        public void setSerialCode(String serialCode) {
            SerialCode = serialCode;
        }
    }
}
