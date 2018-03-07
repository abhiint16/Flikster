package com.flikster.HomeActivity.CommonFragments.MyAccountFragment.UserProfile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 01-02-2018.
 */

public class MyOrderData {
    @SerializedName("hits")
    private InsideOuterHits hits;

    public InsideOuterHits getHits() {
        return hits;
    }

    public void setHits(InsideOuterHits hits) {
        this.hits = hits;
    }

    public class InsideOuterHits {
        @SerializedName("total")
        private Integer total;
        @SerializedName("hits")
        private List<InsideInnerHits> hits;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<InsideInnerHits> getHits() {
            return hits;
        }

        public void setHits(List<InsideInnerHits> hits) {
            this.hits = hits;
        }

        public class InsideInnerHits {
            @SerializedName("_id")
            private String _id;
            @SerializedName("_source")
            private InsideSource _source;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public InsideSource get_source() {
                return _source;
            }

            public void set_source(InsideSource _source) {
                this._source = _source;
            }

            public class InsideSource {
                @SerializedName("userName")
                private String userName;
                @SerializedName("status")
                private String status;
                @SerializedName("createdAt")
                private String createdAt;
                @SerializedName("product")
                private List<InsideProduct> product;

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public List<InsideProduct> getProduct() {
                    return product;
                }

                public void setProduct(List<InsideProduct> product) {
                    this.product = product;
                }

                public class InsideProduct {
                    @SerializedName("size")
                    private String size;
                    @SerializedName("productTitle")
                    private String productTitle;
                    @SerializedName("productId")
                    private String productId;
                    @SerializedName("color")
                    private String color;
                    @SerializedName("price")
                    private String price;
                    @SerializedName("productPic")
                    private String productPic;
                    @SerializedName("quantity")
                    private String quantity;

                    public String getSize() {
                        return size;
                    }

                    public void setSize(String size) {
                        this.size = size;
                    }

                    public String getProductTitle() {
                        return productTitle;
                    }

                    public void setProductTitle(String productTitle) {
                        this.productTitle = productTitle;
                    }

                    public String getProductId() {
                        return productId;
                    }

                    public void setProductId(String productId) {
                        this.productId = productId;
                    }

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }

                    public String getProductPic() {
                        return productPic;
                    }

                    public void setProductPic(String productPic) {
                        this.productPic = productPic;
                    }

                    public String getQuantity() {
                        return quantity;
                    }

                    public void setQuantity(String quantity) {
                        this.quantity = quantity;
                    }
                }
            }
        }
    }
}
