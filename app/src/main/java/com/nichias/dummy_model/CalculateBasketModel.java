package com.nichias.dummy_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CalculateBasketModel {

    @SerializedName("branch_key")
    @Expose
    private String branch_key;
    @SerializedName("order_type")
    @Expose
    private Integer orderType;
    @SerializedName("pickup_delivery_date_time")
    @Expose
    private String pickupDeliveryDateTime;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("order_message")
    @Expose
    private String order_message;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("delivery_boy_tip")
    @Expose
    private String deliveryBoyTip;
    @SerializedName("payment_option")
    @Expose
    private Integer paymentOption;
    @SerializedName("payment_type")
    @Expose
    private String payment_type;
    @SerializedName("delivery_option")
    @Expose
    private String deliveryOption;
    @SerializedName("user_address_key")
    @Expose
    private String user_address_key;

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getUser_address_key() {
        return user_address_key;
    }

    public void setUser_address_key(String user_address_key) {
        this.user_address_key = user_address_key;
    }

    public String getBranch_key() {
        return branch_key;
    }

    public void setBranch_key(String branch_key) {
        this.branch_key = branch_key;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getPickupDeliveryDateTime() {
        return pickupDeliveryDateTime;
    }

    public void setPickupDeliveryDateTime(String pickupDeliveryDateTime) {
        this.pickupDeliveryDateTime = pickupDeliveryDateTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getDeliveryBoyTip() {
        return deliveryBoyTip;
    }

    public void setDeliveryBoyTip(String deliveryBoyTip) {
        this.deliveryBoyTip = deliveryBoyTip;
    }

    public Integer getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(Integer paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public String getOrder_message() {
        return order_message;
    }

    public void setOrder_message(String order_message) {
        this.order_message = order_message;
    }


    public static class Ingrdient {

        @SerializedName("group_key")
        @Expose
        private String groupKey;
        @SerializedName("key")
        @Expose
        private String key;

        public String getGroupKey() {
            return groupKey;
        }

        public void setGroupKey(String groupKey) {
            this.groupKey = groupKey;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    public static class Item {

        @SerializedName("product_key")
        @Expose
        private String itemKey;
        @SerializedName("item_price")
        @Expose
        private String itemPrice;
        @SerializedName("is_parcel")
        @Expose
        private String is_parcel;
        @SerializedName("item_quantity")
        @Expose
        private Integer itemQuantity;
        @SerializedName("ingrdients")
        @Expose
        private List<Ingrdient> ingrdients = null;

        public String getItemKey() {
            return itemKey;
        }

        public void setItemKey(String itemKey) {
            this.itemKey = itemKey;
        }

        public String getItemPrice() {
            return itemPrice;
        }

        public void setItemPrice(String itemPrice) {
            this.itemPrice = itemPrice;
        }

        public String getIs_parcel() {
            return is_parcel;
        }

        public void setIs_parcel(String is_parcel) {
            this.is_parcel = is_parcel;
        }

        public Integer getItemQuantity() {
            return itemQuantity;
        }

        public void setItemQuantity(Integer itemQuantity) {
            this.itemQuantity = itemQuantity;
        }

        public List<Ingrdient> getIngrdients() {
            return ingrdients;
        }

        public void setIngrdients(List<Ingrdient> ingrdients) {
            this.ingrdients = ingrdients;
        }

    }
}