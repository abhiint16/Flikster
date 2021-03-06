package com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 06-11-2017.
 */

public class AllStoreInnerData {

    @SerializedName("total")
    private Integer total;
    @SerializedName("hits")
    private List<AllStoreInnerMoreData> hits;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<AllStoreInnerMoreData> getHits() {
        return hits;
    }

    public void setHits(List<AllStoreInnerMoreData> hits) {
        this.hits = hits;
    }

    public class AllStoreInnerMoreData {
        @SerializedName("_source")
        private AllStoreInnerMostData _source;

        public AllStoreInnerMostData get_source() {
            return _source;
        }

        public void set_source(AllStoreInnerMostData _source) {
            this._source = _source;
        }
    }

    public class AllStoreInnerMostData {
        @SerializedName("celeb")
        private List<AllStoreCelebData> celeb;
        @SerializedName("imageGallery")
        private List<String> imageGallery;
        @SerializedName("name")
        private String name;
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("price")
        private String price;
        @SerializedName("productDescription")
        private String productDescription;
        @SerializedName("brand")
        private String brand;
        @SerializedName("size")
        private List<String> size;
        @SerializedName("id")
        private String id;
        @SerializedName("productInfo")
        private String productInfo;
        @SerializedName("slug")
        private String slug;

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

        public String getProductInfo() {
            return productInfo;
        }

        public void setProductInfo(String productInfo) {
            this.productInfo = productInfo;
        }

        public List<String> getSize() {
            return size;
        }

        public void setSize(List<String> size) {
            this.size = size;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public void setProductDescription(String productDescription) {
            this.productDescription = productDescription;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<AllStoreCelebData> getCeleb() {
            return celeb;
        }

        public void setCeleb(List<AllStoreCelebData> celeb) {
            this.celeb = celeb;
        }

        public List<String> getImageGallery() {
            return imageGallery;
        }

        public void setImageGallery(List<String> imageGallery) {
            this.imageGallery = imageGallery;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public class AllStoreCelebData
        {
            @SerializedName("name")
            private String name;
            @SerializedName("role")
            private List<String> role;
            @SerializedName("profilePic")
            private String profilePic;
            @SerializedName("slug")
            private String slug;

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getRole() {
                return role;
            }

            public void setRole(List<String> role) {
                this.role = role;
            }

            public String getProfilePic() {
                return profilePic;
            }

            public void setProfilePic(String profilePic) {
                this.profilePic = profilePic;
            }
        }
    }


}
