package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RmInventoryApiJson {
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
    @SerializedName("SerialCode")
    @Expose
    private String SerialCode;
    @SerializedName("Supplier")
    @Expose
    private String Supplier;
    @SerializedName("Customer")
    @Expose
    private String Customer;
    @SerializedName("PartName")
    @Expose
    private String PartName;
    @SerializedName("PartNo")
    @Expose
    private String PartNo;
    @SerializedName("MaterialGrade")
    @Expose
    private String MaterialGrade;
    @SerializedName("MaterialName")
    @Expose
    private String MaterialName;
    @SerializedName("Quantity")
    @Expose
    private String Quantity;
    @SerializedName("QAStatus")
    @Expose
    private String QAStatus;
    @SerializedName("QAPerson")
    @Expose
    private String QAPerson;
    @SerializedName("DateReceived")
    @Expose
    private String DateReceived;
    @SerializedName("VerifiedDate")
    @Expose
    private String VerifiedDate;
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

    public String getSerialCode() {
        return SerialCode;
    }

    public void setSerialCode(String serialCode) {
        SerialCode = serialCode;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

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

    public String getMaterialGrade() {
        return MaterialGrade;
    }

    public void setMaterialGrade(String materialGrade) {
        MaterialGrade = materialGrade;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getQAStatus() {
        return QAStatus;
    }

    public void setQAStatus(String QAStatus) {
        this.QAStatus = QAStatus;
    }

    public String getQAPerson() {
        return QAPerson;
    }

    public void setQAPerson(String QAPerson) {
        this.QAPerson = QAPerson;
    }

    public String getDateReceived() {
        return DateReceived;
    }

    public void setDateReceived(String dateReceived) {
        DateReceived = dateReceived;
    }

    public String getVerifiedDate() {
        return VerifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        VerifiedDate = verifiedDate;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
