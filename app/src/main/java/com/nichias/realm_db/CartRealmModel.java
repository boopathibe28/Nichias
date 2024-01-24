package com.nichias.realm_db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CartRealmModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String item_id;
    private String item_key;
    private String item_name;
    private String item_image;
    private String item_type;
    private String item_price_per_unit;
    private String category_id;
    private String ingredient_name;
    private String ingredient_price;
    private String ingredient_id;
    private String vendor_key;
    private String QTY;
    private String Price;
    private String order_total;
    private RealmList<IngredientsRealmModel> IngredientsRealmModel = new RealmList<>();
    private String is_ingredient;
    private String Ingredient_quantity;
    private String General_request;
    private String vendorName;
    private String is_parcel;


    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<IngredientsRealmModel> getIngredientsRealmModel() {
        return IngredientsRealmModel;
    }

    void setIngredientsRealmModel(RealmList<IngredientsRealmModel> ingredientsRealmModel) {
        this.IngredientsRealmModel = ingredientsRealmModel;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_key() {
        return item_key;
    }

    void setItem_key(String item_key) {
        this.item_key = item_key;
    }

    public String getItem_name() {
        return item_name;
    }

    void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_price_per_unit() {
        return item_price_per_unit;
    }

    void setItem_price_per_unit(String item_price_per_unit) {
        this.item_price_per_unit = item_price_per_unit;
    }

    public String getCategory_id() {
        return category_id;
    }

    void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public String getIngredient_price() {
        return ingredient_price;
    }

    public void setIngredient_price(String ingredient_price) {
        this.ingredient_price = ingredient_price;
    }

    public String getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(String ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getVendor_key() {
        return vendor_key;
    }

    void setVendor_key(String vendor_key) {
        this.vendor_key = vendor_key;
    }

    public String getQTY() {
        return QTY;
    }

    void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getIs_ingredient() {
        return is_ingredient;
    }

    void setIs_ingredient(String is_ingredient) {
        this.is_ingredient = is_ingredient;
    }

    public String getIngredient_quantity() {
        return Ingredient_quantity;
    }

    public void setIngredient_quantity(String ingredient_quantity) {
        Ingredient_quantity = ingredient_quantity;
    }

    public String getGeneral_request() {
        return General_request;
    }

    void setGeneral_request(String general_request) {
        General_request = general_request;
    }

    public String getOrder_total() {
        return order_total;
    }

    void setOrder_total(String order_total) {
        this.order_total = order_total;
    }

    public String getIs_parcel() {
        return is_parcel;
    }

    public void setIs_parcel(String is_parcel) {
        this.is_parcel = is_parcel;
    }
}