package com.nichias.model_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SettingsApiResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("system")
    @Expose
    private System system;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }


    public class System {

        @SerializedName("nc")
        @Expose
        private Integer nc;

        public Integer getNc() {
            return nc;
        }

        public void setNc(Integer nc) {
            this.nc = nc;
        }

    }


    public class Datum {

        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("cms_title")
        @Expose
        private String cms_title;
        @SerializedName("cms_url")
        @Expose
        private String cms_url;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getCms_title() {
            return cms_title;
        }

        public void setCms_title(String cms_title) {
            this.cms_title = cms_title;
        }

        public String getCms_url() {
            return cms_url;
        }

        public void setCms_url(String cms_url) {
            this.cms_url = cms_url;
        }

    }

}
