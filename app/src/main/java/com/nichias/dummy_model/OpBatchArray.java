package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nichias.model_api.TrimmedScrapApiResponse;

import java.util.List;

public class OpBatchArray {
    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        private String data1;
        private String data2;
        public String getData1() {
            return data1;
        }
        public void setData1(String data1) {
            this.data1 = data1;
        }
        public String getData2() {
            return data2;
        }
        public void setData2(String data2) {
            this.data2 = data2;
        }
    }
}
