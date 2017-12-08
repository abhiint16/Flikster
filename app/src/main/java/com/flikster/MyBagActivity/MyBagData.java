package com.flikster.MyBagActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 08-12-2017.
 */

public class MyBagData {
    @SerializedName("hits")
    private MyBagInnerData hits;

    public MyBagInnerData getHits() {
        return hits;
    }

    public void setHits(MyBagInnerData hits) {
        this.hits = hits;
    }

    public class MyBagInnerData
    {
        @SerializedName("total")
        private Integer total;
        @SerializedName("hits")
        private List<MyBagInnerMoreData> hits;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<MyBagInnerMoreData> getHits() {
            return hits;
        }

        public void setHits(List<MyBagInnerMoreData> hits) {
            this.hits = hits;
        }

        public class MyBagInnerMoreData
        {
            @SerializedName("_source")
            private MyBagInnerMostData _source;

            public MyBagInnerMostData get_source() {
                return _source;
            }

            public void set_source(MyBagInnerMostData _source) {
                this._source = _source;
            }

            public class MyBagInnerMostData
            {
                @SerializedName("color")
                private String color;
                @SerializedName("userId")
                private String userId;
                @SerializedName("size")
                private String size;
                @SerializedName("productDetails")
                private MyBagProductDetailsData productDetails;

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public MyBagProductDetailsData getProductDetails() {
                    return productDetails;
                }

                public void setProductDetails(MyBagProductDetailsData productDetails) {
                    this.productDetails = productDetails;
                }

                public class MyBagProductDetailsData
                {
                    @SerializedName("productTitle")
                    private String productTitle;
                    @SerializedName("quantity")
                    private Integer quantity;
                    @SerializedName("color")
                    private String color;
                    @SerializedName("productPic")
                    private String productPic;
                    @SerializedName("productSlug")
                    private String productSlug;
                    @SerializedName("size")
                    private String size;

                    public String getProductTitle() {
                        return productTitle;
                    }

                    public void setProductTitle(String productTitle) {
                        this.productTitle = productTitle;
                    }

                    public Integer getQuantity() {
                        return quantity;
                    }

                    public void setQuantity(Integer quantity) {
                        this.quantity = quantity;
                    }

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public String getProductPic() {
                        return productPic;
                    }

                    public void setProductPic(String productPic) {
                        this.productPic = productPic;
                    }

                    public String getProductSlug() {
                        return productSlug;
                    }

                    public void setProductSlug(String productSlug) {
                        this.productSlug = productSlug;
                    }

                    public String getSize() {
                        return size;
                    }

                    public void setSize(String size) {
                        this.size = size;
                    }
                }
            }
        }
    }
}
