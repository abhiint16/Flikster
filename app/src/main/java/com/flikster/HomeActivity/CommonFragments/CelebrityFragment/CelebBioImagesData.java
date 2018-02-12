package com.flikster.HomeActivity.CommonFragments.CelebrityFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 19-12-2017.
 */

public class CelebBioImagesData {
    @SerializedName("celebSlug")
    private String celebSlug;
    @SerializedName("data")
    private List<CelebBioImagesDataInner> data;
    @SerializedName("statusCode")
    private String statusCode;

    public CelebBioImagesData(String celebSlug) {
        this.celebSlug = celebSlug;
    }

    public List<CelebBioImagesDataInner> getData() {
        return data;
    }

    public void setData(List<CelebBioImagesDataInner> data) {
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public  class CelebBioImagesDataInner
    {
        @SerializedName("gallery")
        private String gallery;
        @SerializedName("slug")
        private String slug;
        @SerializedName("id")
        private String id;

        public String getGallery() {
            return gallery;
        }

        public void setGallery(String gallery) {
            this.gallery = gallery;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
