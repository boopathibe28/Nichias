package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FgDispatchApiResponse {

    @SerializedName("result")
    @Expose
    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }


    public static class Result {

        @SerializedName("Length")
        @Expose
        private String Length;
        @SerializedName("FG_Locaton")
        @Expose
        private String FG_Locaton;
        @SerializedName("Batch_No")
        @Expose
        private String Batch_No;
        @SerializedName("Quantity")
        @Expose
        private String Quantity;
        @SerializedName("Grade")
        @Expose
        private String Grade;
        @SerializedName("Barcode_Qty")
        @Expose
        private String Barcode_Qty;
        @SerializedName("Thick")
        @Expose
        private String Thick;
        @SerializedName("Width")
        @Expose
        private String Width;
        @SerializedName("Barcode_Batchno")
        @Expose
        private String Barcode_Batchno;

        public String getLength() {
            return Length;
        }

        public void setLength(String length) {
            Length = length;
        }

        public String getFG_Locaton() {
            return FG_Locaton;
        }

        public void setFG_Locaton(String FG_Locaton) {
            this.FG_Locaton = FG_Locaton;
        }

        public String getBatch_No() {
            return Batch_No;
        }

        public void setBatch_No(String batch_No) {
            Batch_No = batch_No;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String quantity) {
            Quantity = quantity;
        }

        public String getGrade() {
            return Grade;
        }

        public void setGrade(String grade) {
            Grade = grade;
        }

        public String getBarcode_Qty() {
            return Barcode_Qty;
        }

        public void setBarcode_Qty(String barcode_Qty) {
            Barcode_Qty = barcode_Qty;
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

        public String getBarcode_Batchno() {
            return Barcode_Batchno;
        }

        public void setBarcode_Batchno(String barcode_Batchno) {
            Barcode_Batchno = barcode_Batchno;
        }
    }


}
