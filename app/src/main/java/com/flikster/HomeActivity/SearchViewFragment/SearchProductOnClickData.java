package com.flikster.HomeActivity.SearchViewFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 05-01-2018.
 */

public class SearchProductOnClickData {
    @SerializedName("imageGallery")
    private List<String> imageGallery;
    @SerializedName("size")
    private List<String> size;
    @SerializedName("profilePic")
    private String profilePic;
    @SerializedName("name")
    private String name;
    @SerializedName("productInfo")
    private String productInfo;
    @SerializedName("id")
    private String id;
    @SerializedName("color")
    private List<String> color;
    @SerializedName("slug")
    private String slug;
    @SerializedName("productDescription")
    private String productDescription;
    @SerializedName("price")
    private String price;

    public List<String> getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(List<String> imageGallery) {
        this.imageGallery = imageGallery;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
