package com.nichias.model_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobOrdersApiResponse {
    @SerializedName("data")
    @Expose
    private List<Data> data;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("havedata")
    @Expose
    private String havedata;
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

    public String getHavedata() {
        return havedata;
    }

    public void setHavedata(String havedata) {
        this.havedata = havedata;
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
        @SerializedName("LotNo")
        @Expose
        private String LotNo;
        @SerializedName("RMRackNo")
        @Expose
        private String RMRackNo;
        @SerializedName("RollNo")
        @Expose
        private String RollNo;
        @SerializedName("PartName")
        @Expose
        private String PartName;
        @SerializedName("MachineName")
        @Expose
        private String MachineName;
        @SerializedName("ManagedNo")
        @Expose
        private String ManagedNo;
        @SerializedName("Size")
        @Expose
        private String Size;
        @SerializedName("SerialCode")
        @Expose
        private String SerialCode;
        @SerializedName("MaterialName")
        @Expose
        private String MaterialName;

        public String getRMRackNo() {
            return RMRackNo;
        }

        public void setRMRackNo(String RMRackNo) {
            this.RMRackNo = RMRackNo;
        }

        public String getLotNo() {
            return LotNo;
        }

        public void setLotNo(String lotNo) {
            LotNo = lotNo;
        }

        public String getRollNo() {
            return RollNo;
        }

        public void setRollNo(String rollNo) {
            RollNo = rollNo;
        }

        public String getPartName() {
            return PartName;
        }

        public void setPartName(String partName) {
            PartName = partName;
        }

        public String getMachineName() {
            return MachineName;
        }

        public void setMachineName(String machineName) {
            MachineName = machineName;
        }

        public String getManagedNo() {
            return ManagedNo;
        }

        public void setManagedNo(String managedNo) {
            ManagedNo = managedNo;
        }

        public String getSize() {
            return Size;
        }

        public void setSize(String size) {
            Size = size;
        }

        public String getSerialCode() {
            return SerialCode;
        }

        public void setSerialCode(String serialCode) {
            SerialCode = serialCode;
        }

        public String getMaterialName() {
            return MaterialName;
        }

        public void setMaterialName(String materialName) {
            MaterialName = materialName;
        }
    }
}
