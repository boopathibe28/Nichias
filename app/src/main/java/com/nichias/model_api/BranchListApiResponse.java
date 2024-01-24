package com.nichias.model_api;


import java.util.List;

public class BranchListApiResponse {

    private System system;

    private Data data;

    private String time;

    private String message;

    private String status;

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [system = " + system + ", data = " + data + ", time = " + time + ", message = " + message + ", status = " + status + "]";
    }


    public class System {
        private String nc;

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        @Override
        public String toString() {
            return "ClassPojo [nc = " + nc + "]";
        }
    }

    public class Data {
        private List<Featured> featured;

        private List<Lists> list;
        private List<Banner> banner;

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<Featured> getFeatured() {
            return featured;
        }

        public void setFeatured(List<Featured> featured) {
            this.featured = featured;
        }

        public List<Lists> getList() {
            return list;
        }

        public void setList(List<Lists> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "ClassPojo [featured = " + featured + ", list = " + list + "]";
        }
    }


    public class Banner {
        private String banner_image_path;

        private String vendor_id;

        private String banner_link;

        private String banner_name;

        private String branch_key;

        private String vendor_key;
        private String redirect_url;

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }

        public String getBanner_image_path() {
            return banner_image_path;
        }

        public void setBanner_image_path(String banner_image_path) {
            this.banner_image_path = banner_image_path;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getBanner_link() {
            return banner_link;
        }

        public void setBanner_link(String banner_link) {
            this.banner_link = banner_link;
        }

        public String getBanner_name() {
            return banner_name;
        }

        public void setBanner_name(String banner_name) {
            this.banner_name = banner_name;
        }

        public String getBranch_key() {
            return branch_key;
        }

        public void setBranch_key(String branch_key) {
            this.branch_key = branch_key;
        }

        public String getVendor_key() {
            return vendor_key;
        }

        public void setVendor_key(String vendor_key) {
            this.vendor_key = vendor_key;
        }

    }


    public class Vendor_type {
        private String vendor_type_name;

        private String vendor_type_id;

        public String getVendor_type_name() {
            return vendor_type_name;
        }

        public void setVendor_type_name(String vendor_type_name) {
            this.vendor_type_name = vendor_type_name;
        }

        public String getVendor_type_id() {
            return vendor_type_id;
        }

        public void setVendor_type_id(String vendor_type_id) {
            this.vendor_type_id = vendor_type_id;
        }

        @Override
        public String toString() {
            return "ClassPojo [vendor_type_name = " + vendor_type_name + ", vendor_type_id = " + vendor_type_id + "]";
        }
    }


    public class Branch_image_path {
        private String branch_image_path;

        public String getBranch_image_path() {
            return branch_image_path;
        }

        public void setBranch_image_path(String branch_image_path) {
            this.branch_image_path = branch_image_path;
        }

        @Override
        public String toString() {
            return "ClassPojo [branch_image_path = " + branch_image_path + "]";
        }
    }


    public class Lists {
        private String delivery_charge_base_km;

        private String distance;

        private String latitude;

        private String branch_availability_text;

        private String rating;

        private String vendor_name;

        private String delivery_time;

        private String vendor_image_path;

        private String branch_key;

        private String vendor_key;

        private List<Cuisines> cuisines;

        private String branch_availability_status;

        private String busy_reason;

        private String branch_name;

        private String vendor_id;

        private String user_favourite;

        private List<Branch_image_path> branch_image_path;

        private String eta_time;

        private String pickup_time;

        private String minimum_cart_value;

        private String items;

        private String longitude;

        private List<Vendor_type> vendor_type;

        public String getDelivery_charge_base_km() {
            return delivery_charge_base_km;
        }

        public void setDelivery_charge_base_km(String delivery_charge_base_km) {
            this.delivery_charge_base_km = delivery_charge_base_km;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getBranch_availability_text() {
            return branch_availability_text;
        }

        public void setBranch_availability_text(String branch_availability_text) {
            this.branch_availability_text = branch_availability_text;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getVendor_name() {
            return vendor_name;
        }

        public void setVendor_name(String vendor_name) {
            this.vendor_name = vendor_name;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getVendor_image_path() {
            return vendor_image_path;
        }

        public void setVendor_image_path(String vendor_image_path) {
            this.vendor_image_path = vendor_image_path;
        }

        public String getBranch_key() {
            return branch_key;
        }

        public void setBranch_key(String branch_key) {
            this.branch_key = branch_key;
        }

        public String getVendor_key() {
            return vendor_key;
        }

        public void setVendor_key(String vendor_key) {
            this.vendor_key = vendor_key;
        }

        public List<Cuisines> getCuisines() {
            return cuisines;
        }

        public void setCuisines(List<Cuisines> cuisines) {
            this.cuisines = cuisines;
        }

        public List<Branch_image_path> getBranch_image_path() {
            return branch_image_path;
        }

        public void setBranch_image_path(List<Branch_image_path> branch_image_path) {
            this.branch_image_path = branch_image_path;
        }

        public List<Vendor_type> getVendor_type() {
            return vendor_type;
        }

        public void setVendor_type(List<Vendor_type> vendor_type) {
            this.vendor_type = vendor_type;
        }

        public String getBranch_availability_status() {
            return branch_availability_status;
        }

        public void setBranch_availability_status(String branch_availability_status) {
            this.branch_availability_status = branch_availability_status;
        }

        public String getBusy_reason() {
            return busy_reason;
        }

        public void setBusy_reason(String busy_reason) {
            this.busy_reason = busy_reason;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getUser_favourite() {
            return user_favourite;
        }

        public void setUser_favourite(String user_favourite) {
            this.user_favourite = user_favourite;
        }


        public String getEta_time() {
            return eta_time;
        }

        public void setEta_time(String eta_time) {
            this.eta_time = eta_time;
        }

        public String getPickup_time() {
            return pickup_time;
        }

        public void setPickup_time(String pickup_time) {
            this.pickup_time = pickup_time;
        }

        public String getMinimum_cart_value() {
            return minimum_cart_value;
        }

        public void setMinimum_cart_value(String minimum_cart_value) {
            this.minimum_cart_value = minimum_cart_value;
        }

        public String getItems() {
            return items;
        }

        public void setItems(String items) {
            this.items = items;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }


        @Override
        public String toString() {
            return "ClassPojo [delivery_charge_base_km = " + delivery_charge_base_km + ", distance = " + distance + ", latitude = " + latitude + ", branch_availability_text = " + branch_availability_text + ", rating = " + rating + ", vendor_name = " + vendor_name + ", delivery_time = " + delivery_time + ", vendor_image_path = " + vendor_image_path + ", branch_key = " + branch_key + ", vendor_key = " + vendor_key + ", cuisines = " + cuisines + ", branch_availability_status = " + branch_availability_status + ", busy_reason = " + busy_reason + ", branch_name = " + branch_name + ", vendor_id = " + vendor_id + ", user_favourite = " + user_favourite + ", branch_image_path = " + branch_image_path + ", eta_time = " + eta_time + ", pickup_time = " + pickup_time + ", minimum_cart_value = " + minimum_cart_value + ", items = " + items + ", longitude = " + longitude + ", vendor_type = " + vendor_type + "]";
        }
    }


    public class Cuisines {
        private String cuisine_name;

        private String cuisine_id;

        public String getCuisine_name() {
            return cuisine_name;
        }

        public void setCuisine_name(String cuisine_name) {
            this.cuisine_name = cuisine_name;
        }

        public String getCuisine_id() {
            return cuisine_id;
        }

        public void setCuisine_id(String cuisine_id) {
            this.cuisine_id = cuisine_id;
        }

        @Override
        public String toString() {
            return "ClassPojo [cuisine_name = " + cuisine_name + ", cuisine_id = " + cuisine_id + "]";
        }
    }


    public class Featured {
        private String delivery_charge_base_km;

        private String delivery_minutes;

        private String rating;

        private String vendor_name;

        private String vendor_image_path;

        private List<Cuisines> cuisines;

        public String getDelivery_charge_base_km() {
            return delivery_charge_base_km;
        }

        public void setDelivery_charge_base_km(String delivery_charge_base_km) {
            this.delivery_charge_base_km = delivery_charge_base_km;
        }

        public String getDelivery_minutes() {
            return delivery_minutes;
        }

        public void setDelivery_minutes(String delivery_minutes) {
            this.delivery_minutes = delivery_minutes;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getVendor_name() {
            return vendor_name;
        }

        public void setVendor_name(String vendor_name) {
            this.vendor_name = vendor_name;
        }

        public String getVendor_image_path() {
            return vendor_image_path;
        }

        public void setVendor_image_path(String vendor_image_path) {
            this.vendor_image_path = vendor_image_path;
        }

        public List<Cuisines> getCuisines() {
            return cuisines;
        }

        public void setCuisines(List<Cuisines> cuisines) {
            this.cuisines = cuisines;
        }

        @Override
        public String toString() {
            return "ClassPojo [delivery_charge_base_km = " + delivery_charge_base_km + ", delivery_minutes = " + delivery_minutes + ", rating = " + rating + ", vendor_name = " + vendor_name + ", vendor_image_path = " + vendor_image_path + ", cuisines = " + cuisines + "]";
        }
    }


}
