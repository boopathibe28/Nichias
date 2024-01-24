package com.nichias.model_api;

import java.util.List;

public class TrimmedScrapApiResponse {
    private List<Result> result;
    private String msg;
    private String code;
    private String status;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Result {
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
