package com.nichias.model_api;

import java.util.List;

public class BranchViewDetailsApiResponse {
    private System system;

    private List<Data> data;

    private String time;

    private String message;

    private String status;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public System getSystem ()
    {
        return system;
    }

    public void setSystem (System system)
    {
        this.system = system;
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
        private String product_count;

        private String vendor_name;

        private List<Categories> categories;

        private String vendor_image_path;

        private String vendor_key;

        public List<Categories> getCategories() {
            return categories;
        }

        public void setCategories(List<Categories> categories) {
            this.categories = categories;
        }

        public String getProduct_count ()
        {
            return product_count;
        }

        public void setProduct_count (String product_count)
        {
            this.product_count = product_count;
        }

        public String getVendor_name ()
        {
            return vendor_name;
        }

        public void setVendor_name (String vendor_name)
        {
            this.vendor_name = vendor_name;
        }



        public String getVendor_image_path ()
        {
            return vendor_image_path;
        }

        public void setVendor_image_path (String vendor_image_path)
        {
            this.vendor_image_path = vendor_image_path;
        }

        public String getVendor_key ()
        {
            return vendor_key;
        }

        public void setVendor_key (String vendor_key)
        {
            this.vendor_key = vendor_key;
        }


    }





    public class Categories
    {
        private String category_key;

        private String category_name;

        private String category_id;

        private List<Subcategories> subcategories;

        public List<Subcategories> getSubcategories() {
            return subcategories;
        }

        public void setSubcategories(List<Subcategories> subcategories) {
            this.subcategories = subcategories;
        }

        public String getCategory_key ()
        {
            return category_key;
        }

        public void setCategory_key (String category_key)
        {
            this.category_key = category_key;
        }

        public String getCategory_name ()
        {
            return category_name;
        }

        public void setCategory_name (String category_name)
        {
            this.category_name = category_name;
        }

        public String getCategory_id ()
        {
            return category_id;
        }

        public void setCategory_id (String category_id)
        {
            this.category_id = category_id;
        }



        @Override
        public String toString()
        {
            return "ClassPojo [category_key = "+category_key+", category_name = "+category_name+", category_id = "+category_id+", subcategories = "+subcategories+"]";
        }
    }





    public class Subcategories
    {
        private String category_key;

        private String category_name;

        private String category_id;

        public String getCategory_key ()
        {
            return category_key;
        }

        public void setCategory_key (String category_key)
        {
            this.category_key = category_key;
        }

        public String getCategory_name ()
        {
            return category_name;
        }

        public void setCategory_name (String category_name)
        {
            this.category_name = category_name;
        }

        public String getCategory_id ()
        {
            return category_id;
        }

        public void setCategory_id (String category_id)
        {
            this.category_id = category_id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [category_key = "+category_key+", category_name = "+category_name+", category_id = "+category_id+"]";
        }
    }




}
