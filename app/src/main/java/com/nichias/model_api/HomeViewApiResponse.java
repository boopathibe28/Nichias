package com.nichias.model_api;

import java.util.List;

public class HomeViewApiResponse {
    private System system;

    private Data data;

    private String time;

    private String message;

    private String status;

    public System getSystem ()
    {
        return system;
    }

    public void setSystem (System system)
    {
        this.system = system;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [system = "+system+", data = "+data+", time = "+time+", message = "+message+", status = "+status+"]";
    }

    public class System
    {
        private String nc;

        public String getNc ()
        {
            return nc;
        }

        public void setNc (String nc)
        {
            this.nc = nc;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [nc = "+nc+"]";
        }
    }



    public class Data
    {
        private List<Cusines> cusines;

        private List<Banner> banner;

        public List<Cusines> getCusines() {
            return cusines;
        }

        public void setCusines(List<Cusines> cusines) {
            this.cusines = cusines;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }
    }



    public class Cusines
    {
        private String cuisine_name;

        private String cuisine_id;

        private String cuisine_key;

        private String cuisine_image_path;

        public String getCuisine_name ()
        {
            return cuisine_name;
        }

        public void setCuisine_name (String cuisine_name)
        {
            this.cuisine_name = cuisine_name;
        }

        public String getCuisine_id ()
        {
            return cuisine_id;
        }

        public void setCuisine_id (String cuisine_id)
        {
            this.cuisine_id = cuisine_id;
        }

        public String getCuisine_key ()
        {
            return cuisine_key;
        }

        public void setCuisine_key (String cuisine_key)
        {
            this.cuisine_key = cuisine_key;
        }

        public String getCuisine_image_path ()
        {
            return cuisine_image_path;
        }

        public void setCuisine_image_path (String cuisine_image_path)
        {
            this.cuisine_image_path = cuisine_image_path;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [cuisine_name = "+cuisine_name+", cuisine_id = "+cuisine_id+", cuisine_key = "+cuisine_key+", cuisine_image_path = "+cuisine_image_path+"]";
        }
    }


    public class Banner
    {
        private String banner_image_path;

        private String banner_link;

        private String banner_name;

        private String branch_key;

        private String vendor_key;

        private String vendor_id;
        private String redirect_url;

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getBanner_image_path ()
        {
            return banner_image_path;
        }

        public void setBanner_image_path (String banner_image_path)
        {
            this.banner_image_path = banner_image_path;
        }

        public String getBanner_link ()
        {
            return banner_link;
        }

        public void setBanner_link (String banner_link)
        {
            this.banner_link = banner_link;
        }

        public String getBanner_name ()
        {
            return banner_name;
        }

        public void setBanner_name (String banner_name)
        {
            this.banner_name = banner_name;
        }

        public String getBranch_key ()
        {
            return branch_key;
        }

        public void setBranch_key (String branch_key)
        {
            this.branch_key = branch_key;
        }

        public String getVendor_key ()
        {
            return vendor_key;
        }

        public void setVendor_key (String vendor_key)
        {
            this.vendor_key = vendor_key;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [banner_image_path = "+banner_image_path+", banner_link = "+banner_link+", banner_name = "+banner_name+", branch_key = "+branch_key+", vendor_key = "+vendor_key+"]";
        }
    }


}
