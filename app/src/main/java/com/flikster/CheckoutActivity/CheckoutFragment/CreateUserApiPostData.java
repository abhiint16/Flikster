package com.flikster.CheckoutActivity.CheckoutFragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 12-12-2017.
 */

public class CreateUserApiPostData {

    @SerializedName("userId")
    private String userId;
    @SerializedName("product")
    private List<ProductData> productData;
    @SerializedName("shippingAddress")
    private ShippingAddress shippingAddress;
    @SerializedName("statusCode")
    private Integer statusCode;
    @SerializedName("message")
    private String message;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public CreateUserApiPostData(String userId, List<ProductData> productData, ShippingAddress shippingAddress) {
        this.userId = userId;
        this.productData = productData;
        this.shippingAddress = shippingAddress;
    }




    public List<ProductData> getProductData() {
        return productData;
    }

    public void setProductData(List<ProductData> productData) {
        this.productData = productData;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ProductData
    {
        @SerializedName("productId")
        private String productId;
        @SerializedName("productTitle")
        private String productTitle;
        @SerializedName("productSlug")
        private String productSlug;
        @SerializedName("productPic")
        private String productPic;
        @SerializedName("color")
        private String color;
        @SerializedName("price")
        private String price;
        @SerializedName("size")
        private String size;
        @SerializedName("quantity")
        private String quantity;

        public ProductData(String productId, String productTitle, String productSlug,
                           String productPic, String color, String price, String size, String quantity) {
            this.productId = productId;
            this.productTitle = productTitle;
            this.productSlug = productSlug;
            this.productPic = productPic;
            this.color = color;
            this.price = price;
            this.size = size;
            this.quantity = quantity;
        }
    }

    public static class ShippingAddress
    {
        @SerializedName("name")
        private String name;
        @SerializedName("mobileNo")
        private String mobileNo;
        @SerializedName("address")
        private String address;
        @SerializedName("city")
        private String city;
        @SerializedName("state")
        private String state;
        @SerializedName("pinCode")
        private String pinCode;
        @SerializedName("landmark")
        private String landmark;

        public ShippingAddress(String name, String mobileNo, String address, String city, String state, String pinCode, String landmark) {
            this.name = name;
            this.mobileNo = mobileNo;
            this.address = address;
            this.city = city;
            this.state = state;
            this.pinCode = pinCode;
            this.landmark = landmark;
        }
    }
}
