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
        @SerializedName("price")
        private String price;
        @SerializedName("productDescription")
        private String productDescription;

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
