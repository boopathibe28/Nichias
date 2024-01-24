package com.nichias.model_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BranchViewApiResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
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



    public class Data{
        @SerializedName("vendor_key")
        @Expose
        private String vendor_key;
        @SerializedName("vendor_name")
        @Expose
        private String vendor_name;
        @SerializedName("vendor_image_path")
        @Expose
        private String vendor_image_path;
        @SerializedName("product_count")
        @Expose
        private String product_count;
        @SerializedName("categories")
        @Expose
        private List<Category> categories;

        public String getVendor_key() {
            return vendor_key;
        }

        public void setVendor_key(String vendor_key) {
            this.vendor_key = vendor_key;
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

        public String getProduct_count() {
            return product_count;
        }

        public void setProduct_count(String product_count) {
            this.product_count = product_count;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }
    }
    public class Category {

        @SerializedName("category_id")
        @Expose
        private Integer category_id;
        @SerializedName("category_name")
        @Expose
        private String category_name;
        @SerializedName("category_key")
        @Expose
        private String category_key;
        @SerializedName("subcategories")
        @Expose
        private List<SubCategory> subcategories;

        public Integer getCategory_id() {
            return category_id;
        }

        public void setCategory_id(Integer category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getCategory_key() {
            return category_key;
        }

        public void setCategory_key(String category_key) {
            this.category_key = category_key;
        }

        public List<SubCategory> getSubcategories() {
            return subcategories;
        }

        public void setSubcategories(List<SubCategory> subcategories) {
            this.subcategories = subcategories;
        }
    }

    public class SubCategory{
        @SerializedName("category_id")
        @Expose
        private String category_id;
        @SerializedName("category_key")
        @Expose
        private String category_key;
        @SerializedName("category_name")
        @Expose
        private String category_name;

        @SerializedName("products")
        @Expose
        private List<Products> products;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_key() {
            return category_key;
        }

        public void setCategory_key(String category_key) {
            this.category_key = category_key;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public List<Products> getProducts() {
            return products;
        }

        public void setProducts(List<Products> products) {
            this.products = products;
        }
    }

    public class Products{
        @SerializedName("product_key")
        @Expose
        private String product_key;
        @SerializedName("product_id")
        @Expose
        private String product_id;
        @SerializedName("vendor_id")
        @Expose
        private String vendor_id;
        @SerializedName("vendor_key")
        @Expose
        private String vendor_key;
        @SerializedName("category_key")
        @Expose
        private String category_key;
        @SerializedName("product_name")
        @Expose
        private String product_name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("short_description")
        @Expose
        private String short_description;
        @SerializedName("bar_code")
        @Expose
        private String bar_code;
        @SerializedName("is_new")
        @Expose
        private String is_new;
        @SerializedName("minimum_order_quantity")
        @Expose
        private String minimum_order_quantity;
        @SerializedName("offer_percentage")
        @Expose
        private String offer_percentage;
        @SerializedName("product_price")
        @Expose
        private String product_price;
        @SerializedName("is_favourite")
        @Expose
        private String is_favourite;
        @SerializedName("product_images")
        @Expose
        private List<Product_images> product_images;
        @SerializedName("product_variants")
        @Expose
        private List<Product_variants> product_variants;

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getIs_favourite() {
            return is_favourite;
        }

        public void setIs_favourite(String is_favourite) {
            this.is_favourite = is_favourite;
        }

        public List<Product_variants> getProduct_variants() {
            return product_variants;
        }

        public void setProduct_variants(List<Product_variants> product_variants) {
            this.product_variants = product_variants;
        }

        public String getProduct_key() {
            return product_key;
        }

        public void setProduct_key(String product_key) {
            this.product_key = product_key;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        public String getVendor_key() {
            return vendor_key;
        }

        public void setVendor_key(String vendor_key) {
            this.vendor_key = vendor_key;
        }

        public String getCategory_key() {
            return category_key;
        }

        public void setCategory_key(String category_key) {
            this.category_key = category_key;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }

        public String getMinimum_order_quantity() {
            return minimum_order_quantity;
        }

        public void setMinimum_order_quantity(String minimum_order_quantity) {
            this.minimum_order_quantity = minimum_order_quantity;
        }

        public String getOffer_percentage() {
            return offer_percentage;
        }

        public void setOffer_percentage(String offer_percentage) {
            this.offer_percentage = offer_percentage;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public List<Product_images> getProduct_images() {
            return product_images;
        }

        public void setProduct_images(List<Product_images> product_images) {
            this.product_images = product_images;
        }
    }

    public class Product_images{
        @SerializedName("product_image")
        @Expose
        private String product_image;

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }
    }
    public class Product_variants{
        @SerializedName("product_attribute_id")
        @Expose
        private String product_attribute_id;
        @SerializedName("product_attribute_name")
        @Expose
        private String product_attribute_name;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("stock")
        @Expose
        private String stock;
        @SerializedName("bar_code")
        @Expose
        private String bar_code;


    }
/*
    public class Cuisine {

        @SerializedName("cuisine_id")
        @Expose
        private String cuisine_id;
        @SerializedName("cuisine_name")
        @Expose
        private String cuisine_name;

        public String getCuisine_id() {
            return cuisine_id;
        }

        public void setCuisine_id(String cuisine_id) {
            this.cuisine_id = cuisine_id;
        }

        public String getCuisine_name() {
            return cuisine_name;
        }

        public void setCuisine_name(String cuisine_name) {
            this.cuisine_name = cuisine_name;
        }

    }
*/

/*
    public class Datum {

        @SerializedName("branch_key")
        @Expose
        private String branch_key;
        @SerializedName("branch_name")
        @Expose
        private String branch_name;
        @SerializedName("contact_number")
        @Expose
        private String contact_number;
        @SerializedName("branch_image_path")
        @Expose
        private List<Branch_image_path> branch_image_path;
        @SerializedName("deliver_option")
        @Expose
        private String deliver_option;
        @SerializedName("delivery_time")
        @Expose
        private String delivery_time;
        @SerializedName("delivery_charge_base_km")
        @Expose
        private String delivery_charge_base_km;
        @SerializedName("branch_availability_status")
        @Expose
        private String branch_availability_status;
        @SerializedName("busy_reason")
        @Expose
        private String busy_reason;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("vendor_name")
        @Expose
        private String vendor_name;
        @SerializedName("user_favourite")
        @Expose
        private String user_favourite;
        @SerializedName("vendor_image_path")
        @Expose
        private String vendor_image_path;
        @SerializedName("vendor_description")
        @Expose
        private String vendor_description;
        @SerializedName("cuisines")
        @Expose
        private List<Cuisine> cuisines = null;
        @SerializedName("vendor_type")
        @Expose
        private List<Vendor_type> vendor_type = null;
        @SerializedName("preorder_available")
        @Expose
        private String preorder_available;
        @SerializedName("payment_option")
        @Expose
        private String payment_option;
        @SerializedName("minimum_cart_value")
        @Expose
        private String minimum_cart_value;
        @SerializedName("eta_time")
        @Expose
        private String eta_time;
        @SerializedName("category")
        @Expose
        private List<Category> category = null;
        @SerializedName("items")
        @Expose
        private List<Item> items = null;

        public String getBranch_name() {
            return branch_name;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        public String getBranch_key() {
            return branch_key;
        }

        public void setBranch_key(String branch_key) {
            this.branch_key = branch_key;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getDelivery_charge_base_km() {
            return delivery_charge_base_km;
        }

        public void setDelivery_charge_base_km(String delivery_charge_base_km) {
            this.delivery_charge_base_km = delivery_charge_base_km;
        }

        public List<Branch_image_path> getBranch_image_path() {
            return branch_image_path;
        }

        public void setBranch_image_path(List<Branch_image_path> branch_image_path) {
            this.branch_image_path = branch_image_path;
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

        public String getVendor_name() {
            return vendor_name;
        }

        public void setVendor_name(String vendor_name) {
            this.vendor_name = vendor_name;
        }

        public String getUser_favourite() {
            return user_favourite;
        }

        public void setUser_favourite(String user_favourite) {
            this.user_favourite = user_favourite;
        }

        public String getVendor_image_path() {
            return vendor_image_path;
        }

        public void setVendor_image_path(String vendor_image_path) {
            this.vendor_image_path = vendor_image_path;
        }

        public String getVendor_description() {
            return vendor_description;
        }

        public void setVendor_description(String vendor_description) {
            this.vendor_description = vendor_description;
        }

        public List<Cuisine> getCuisines() {
            return cuisines;
        }

        public void setCuisines(List<Cuisine> cuisines) {
            this.cuisines = cuisines;
        }

        public List<Vendor_type> getVendor_type() {
            return vendor_type;
        }

        public void setVendor_type(List<Vendor_type> vendor_type) {
            this.vendor_type = vendor_type;
        }

        public String getPreorder_available() {
            return preorder_available;
        }

        public void setPreorder_available(String preorder_available) {
            this.preorder_available = preorder_available;
        }

        public String getPayment_option() {
            return payment_option;
        }

        public void setPayment_option(String payment_option) {
            this.payment_option = payment_option;
        }

        public String getMinimum_cart_value() {
            return minimum_cart_value;
        }

        public void setMinimum_cart_value(String minimum_cart_value) {
            this.minimum_cart_value = minimum_cart_value;
        }

        public String getEta_time() {
            return eta_time;
        }

        public void setEta_time(String eta_time) {
            this.eta_time = eta_time;
        }

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
    }
*/

   /* public class Branch_image_path
    {
        private String branch_image_path;

        public String getBranch_image_path ()
        {
            return branch_image_path;
        }

        public void setBranch_image_path (String branch_image_path)
        {
            this.branch_image_path = branch_image_path;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [branch_image_path = "+branch_image_path+"]";
        }
    }

    public class Ingredient {

        @SerializedName("ingredient_key")
        @Expose
        private String ingredient_key;
        @SerializedName("ingredient_name")
        @Expose
        private String ingredient_name;
        @SerializedName("default_status")
        @Expose
        private Integer default_status;
        @SerializedName("price")
        @Expose
        private String price;

        public String getIngredient_key() {
            return ingredient_key;
        }

        public void setIngredient_key(String ingredient_key) {
            this.ingredient_key = ingredient_key;
        }

        public String getIngredient_name() {
            return ingredient_name;
        }

        public void setIngredient_name(String ingredient_name) {
            this.ingredient_name = ingredient_name;
        }

        public Integer getDefault_status() {
            return default_status;
        }

        public void setDefault_status(Integer default_status) {
            this.default_status = default_status;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

    }

    public class Item {

        @SerializedName("item_key")
        @Expose
        private String item_key;
        @SerializedName("item_stock")
        @Expose
        private String item_stock;
        @SerializedName("is_img")
        @Expose
        private String is_img;
        @SerializedName("item_price")
        @Expose
        private String item_price;
        @SerializedName("item_name")
        @Expose
        private String item_name;
        @SerializedName("item_description")
        @Expose
        private String item_description;
        @SerializedName("allergic_ingredients")
        @Expose
        private String allergic_ingredients;

        @SerializedName("is_recommended")
        @Expose
        private Integer is_recommended;
        @SerializedName("item_image_path")
        @Expose
        private List<Item_image_path> item_image_path = null;
        @SerializedName("item_ingredient")
        @Expose
        private List<Item_ingredient> item_ingredient = null;
        @SerializedName("item_type")
        @Expose
        private List<Item_type> item_type = null;
        @SerializedName("category_id")
        @Expose
        private Integer category_id;

        public String getItem_stock() {
            return item_stock;
        }

        public void setItem_stock(String item_stock) {
            this.item_stock = item_stock;
        }

        public String getItem_key() {
            return item_key;
        }

        public void setItem_key(String item_key) {
            this.item_key = item_key;
        }

        public String getItem_price() {
            return item_price;
        }

        public String getIs_img() {
            return is_img;
        }

        public void setIs_img(String is_img) {
            this.is_img = is_img;
        }

        public void setItem_price(String item_price) {
            this.item_price = item_price;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_description() {
            return item_description;
        }

        public void setItem_description(String item_description) {
            this.item_description = item_description;
        }

        public Integer getIs_recommended() {
            return is_recommended;
        }

        public void setIs_recommended(Integer is_recommended) {
            this.is_recommended = is_recommended;
        }

        public List<Item_image_path> getItem_image_path() {
            return item_image_path;
        }

        public void setItem_image_path(List<Item_image_path> item_image_path) {
            this.item_image_path = item_image_path;
        }

        public List<Item_ingredient> getItem_ingredient() {
            return item_ingredient;
        }

        public void setItem_ingredient(List<Item_ingredient> item_ingredient) {
            this.item_ingredient = item_ingredient;
        }

        public List<Item_type> getItem_type() {
            return item_type;
        }

        public void setItem_type(List<Item_type> item_type) {
            this.item_type = item_type;
        }

        public Integer getCategory_id() {
            return category_id;
        }

        public void setCategory_id(Integer category_id) {
            this.category_id = category_id;
        }

        public String getAllergic_ingredients() {
            return allergic_ingredients;
        }

        public void setAllergic_ingredients(String allergic_ingredients) {
            this.allergic_ingredients = allergic_ingredients;
        }
    }

    public class Item_image_path {

        @SerializedName("item_image_path")
        @Expose
        private String item_image_path;
        @SerializedName("language_code")
        @Expose
        private String language_code;

        public String getItem_image_path() {
            return item_image_path;
        }

        public void setItem_image_path(String item_image_path) {
            this.item_image_path = item_image_path;
        }

        public String getLanguage_code() {
            return language_code;
        }

        public void setLanguage_code(String language_code) {
            this.language_code = language_code;
        }

    }

    public class Item_ingredient {

        @SerializedName("item_ingredient_group_key")
        @Expose
        private String item_ingredient_group_key;
        @SerializedName("ingredient_type")
        @Expose
        private Integer ingredient_type;
        @SerializedName("minimum")
        @Expose
        private Integer minimum;
        @SerializedName("maximum")
        @Expose
        private Integer maximum;
        @SerializedName("group_name")
        @Expose
        private String group_name;
        @SerializedName("ingredients")
        @Expose
        private List<Ingredient> ingredients = null;

        public String getItem_ingredient_group_key() {
            return item_ingredient_group_key;
        }

        public void setItem_ingredient_group_key(String item_ingredient_group_key) {
            this.item_ingredient_group_key = item_ingredient_group_key;
        }

        public Integer getIngredient_type() {
            return ingredient_type;
        }

        public void setIngredient_type(Integer ingredient_type) {
            this.ingredient_type = ingredient_type;
        }

        public Integer getMinimum() {
            return minimum;
        }

        public void setMinimum(Integer minimum) {
            this.minimum = minimum;
        }

        public Integer getMaximum() {
            return maximum;
        }

        public void setMaximum(Integer maximum) {
            this.maximum = maximum;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
        }

    }

    public class Item_type {

        @SerializedName("item_type_name")
        @Expose
        private String item_type_name;

        public String getItem_type_name() {
            return item_type_name;
        }

        public void setItem_type_name(String item_type_name) {
            this.item_type_name = item_type_name;
        }

    }*/

    public class System {

        @SerializedName("nc")
        @Expose
        private String nc;

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

    }

/*
    public class Vendor_type {

        @SerializedName("vendor_type_id")
        @Expose
        private String vendor_type_id;
        @SerializedName("vendor_type_name")
        @Expose
        private String vendor_type_name;

        public String getVendor_type_id() {
            return vendor_type_id;
        }

        public void setVendor_type_id(String vendor_type_id) {
            this.vendor_type_id = vendor_type_id;
        }

        public String getVendor_type_name() {
            return vendor_type_name;
        }

        public void setVendor_type_name(String vendor_type_name) {
            this.vendor_type_name = vendor_type_name;
        }

    }
*/

}
