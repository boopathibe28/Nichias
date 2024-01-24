package com.nichias.model_api;

import java.util.ArrayList;
import java.util.List;

public class OnDemandProductInformation {
    private String delivery_date;
    private Pickup pickup;
    private String delivery_time;
    private String user_address_key;
    private String payment_type;

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getUser_address_key() {
        return user_address_key;
    }

    public void setUser_address_key(String user_address_key) {
        this.user_address_key = user_address_key;
    }

    private List<ProductInfo> products;

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }

    public static class ProductInfo  {
        private ArrayList<String> product_image;

        private String product_name;

        public  ArrayList<String> getProduct_images ()
        {
            return product_image;
        }

        public void setProduct_images ( ArrayList<String> product_images)
        {
            this.product_image = product_images;
        }

        public String getProduct_name ()
        {
            return product_name;
        }

        public void setProduct_name (String product_name)
        {
            this.product_name = product_name;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [product_images = "+product_image+", product_name = "+product_name+"]";
        }
    }


    public static class Pickup
    {
        private String pickup_longitude;

        private String pickup_latitude;

        private String pickup_address;

        public String getPickup_longitude ()
        {
            return pickup_longitude;
        }

        public void setPickup_longitude (String pickup_longitude)
        {
            this.pickup_longitude = pickup_longitude;
        }

        public String getPickup_latitude ()
        {
            return pickup_latitude;
        }

        public void setPickup_latitude (String pickup_latitude)
        {
            this.pickup_latitude = pickup_latitude;
        }

        public String getPickup_address ()
        {
            return pickup_address;
        }

        public void setPickup_address (String pickup_address)
        {
            this.pickup_address = pickup_address;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [pickup_longitude = "+pickup_longitude+", pickup_latitude = "+pickup_latitude+", pickup_address = "+pickup_address+"]";
        }
    }

}
