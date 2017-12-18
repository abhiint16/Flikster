package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import com.flikster.CheckoutActivity.CheckoutFragment.CreateUserApiPostData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Logins on 18-12-2017.
 */

public class CreateShareYourStyleData {
    @SerializedName("userId")
    private String userId;

    @SerializedName("image")
    ArrayList<String> images;

    @Expose
    @SerializedName("product")
    private List<Product> productData;


    @SerializedName("statusCode")
    private String statusCode;
    @SerializedName("message")
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public List<Product> getProductData() {
        return productData;
    }

    public void setProductData(List<Product> productData) {
        this.productData = productData;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStyleTemplate() {
        return styleTemplate;
    }

    public void setStyleTemplate(String styleTemplate) {
        this.styleTemplate = styleTemplate;
    }

    public List<UserObject> getUserObject() {
        return userObject;
    }

    public void setUserObject(List<UserObject> userObject) {
        this.userObject = userObject;
    }

    public CreateShareYourStyleData(String userId, ArrayList<String> images,
                                    List<Product> productData, String title,
                                    String slug, String styleTemplate, List<UserObject> userObject) {
        this.userId = userId;
        this.images = images;
        this.productData = productData;
        this.title = title;
        this.slug = slug;
        this.styleTemplate = styleTemplate;
        this.userObject = userObject;
    }

    public static class Product {
        @SerializedName("name")
        private String name;
        @SerializedName("slug")
        private String slug;
        @SerializedName("profilePic")
        private String profilePic;

        public Product(String name, String slug, String profilePic) {
            this.name = name;
            this.slug = slug;
            this.profilePic = profilePic;
        }
    }

    @SerializedName("title")
    private String title;


    @SerializedName("slug")
    private String slug;


    @SerializedName("styleTemplate")
    private String styleTemplate;


    @Expose
    @SerializedName("userObject")
    private List<UserObject> userObject;

    public static class UserObject {
        @SerializedName("profilePic")
        private String profilePic;

        @SerializedName("username")
        private String username;

        public UserObject(String profilePic, String username) {
            this.profilePic = profilePic;
            this.username = username;
        }
    }
}
