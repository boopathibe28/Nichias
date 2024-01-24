package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RMInwardAPIJson {
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("RollNo")
    @Expose
    private String RollNo;
    @SerializedName("LotNo")
    @Expose
    private String LotNo;
    @SerializedName("Size")
    @Expose
    private String Size;
    @SerializedName("ManagedNo")
    @Expose
    private String ManagedNo;
    @SerializedName("MaterialName")
    @Expose
    private String MaterialName;
    @SerializedName("MaterialGrade")
    @Expose
    private String MaterialGrade;
    @SerializedName("Supplier")
    @Expose
    private String Supplier;
    @SerializedName("Quantity")
    @Expose
    private String Quantity;
    @SerializedName("ReceivedDate")
    @Expose
    private String ReceivedDate;
    @SerializedName("InwardDate")
    @Expose
    private String InwardDate;
    @SerializedName("InwardArray")
    @Expose
    private List<InwardArray> inwardArray;
    @SerializedName("Remarks")
    @Expose
    private String Remarks;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String lotNo) {
        LotNo = lotNo;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getManagedNo() {
        return ManagedNo;
    }

    public void setManagedNo(String managedNo) {
        ManagedNo = managedNo;
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

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getReceivedDate() {
        return ReceivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        ReceivedDate = receivedDate;
    }

    public String getInwardDate() {
        return InwardDate;
    }

    public void setInwardDate(String inwardDate) {
        InwardDate = inwardDate;
    }

    public List<InwardArray> getInwardArray() {
        return inwardArray;
    }

    public void setInwardArray(List<InwardArray> inwardArray) {
        this.inwardArray = inwardArray;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
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
