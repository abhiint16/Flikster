package com.flikster.HomeActivity.CommonFragments.ProductFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhishek on 07-12-2017.
 */

public class ProductDetailsDataToSend {
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

    public ProductDetailsDataToSend(String price, String productId, String profilePic, String productTitle,String productSlug, int quantity) {
        this.price = price;
        this.productId = productId;
        this.productPic = profilePic;
        this.productTitle = productTitle;
        this.productSlug = productSlug;
        this.quantity = quantity;
    }
}
