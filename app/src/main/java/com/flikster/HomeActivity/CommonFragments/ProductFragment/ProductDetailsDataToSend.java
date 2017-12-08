package com.flikster.HomeActivity.CommonFragments.ProductFragment;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 07-12-2017.
 */

public class ProductDetailsDataToSend {

    @SerializedName("userId")
    private String userId;
    @SerializedName("productId")
    private String productId;
    @SerializedName("size")
    private String size;
    @SerializedName("color")
    private String color;
    @SerializedName("statusCode")
    private String statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("productDetails")
    @Expose
    private  InnerProductData innerProductData;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductDetailsDataToSend(String userId, String productId, String size, String color, InnerProductData innerProductData) {
        this.userId = userId;
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.innerProductData = innerProductData;
    }

    public static class InnerProductData
    {
        @SerializedName("price")
        private String price;
        @SerializedName("productId")
        private String productId;
        @SerializedName("productPic")
        private String productPic;
        @SerializedName("productTitle")
        private String productTitle;
        @SerializedName("productSlug")
        private String productSlug;
        @SerializedName("quantity")
        private Integer quantity;
        @SerializedName("color")
        private String color;
        @SerializedName("size")
        private String size;

        public InnerProductData(String price, String productId, String productPic, String productTitle, String productSlug, Integer quantity, String color, String size) {
            this.price = price;
            this.productId = productId;
            this.productPic = productPic;
            this.productTitle = productTitle;
            this.productSlug = productSlug;
            this.quantity = quantity;
            this.color = color;
            this.size = size;
        }
    }
}
