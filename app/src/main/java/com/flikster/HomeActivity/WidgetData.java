package com.flikster.HomeActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 02-12-2017.
 */

public class WidgetData {
    @SerializedName("hits")
    private WidgetInnerData hits;

    public WidgetInnerData getHits() {
        return hits;
    }

    public void setHits(WidgetInnerData hits) {
        this.hits = hits;
    }

    public class WidgetInnerData
    {
        @SerializedName("total")
        private Integer total;
        @SerializedName("hits")
        private List<WidgetInnerInnerData> hits;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<WidgetInnerInnerData> getHits() {
            return hits;
        }

        public void setHits(List<WidgetInnerInnerData> hits) {
            this.hits = hits;
        }

        public class WidgetInnerInnerData
        {
            @SerializedName("_source")
            private WidgetInnerMostData _source;

            public WidgetInnerMostData get_source() {
                return _source;
            }

            public void set_source(WidgetInnerMostData _source) {
                this._source = _source;
            }

            public class WidgetInnerMostData
            {
                @SerializedName("title")
                private String title;
                @SerializedName("image")
                private String image;
                @SerializedName("item")
                private WidgetItemData item;
                @SerializedName("products")
                private List<WidgetProductsData> products;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public WidgetItemData getItem() {
                    return item;
                }

                public void setItem(WidgetItemData item) {
                    this.item = item;
                }

                public List<WidgetProductsData> getProducts() {
                    return products;
                }

                public void setProducts(List<WidgetProductsData> products) {
                    this.products = products;
                }

                public class WidgetItemData
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

                public class WidgetProductsData
                {
                    @SerializedName("_source")
                    private WidgetProductInnerData _source;

                    public WidgetProductInnerData get_source() {
                        return _source;
                    }

                    public void set_source(WidgetProductInnerData _source) {
                        this._source = _source;
                    }

                    public class WidgetProductInnerData
                    {
                        @SerializedName("name")
                        private String name;
                        @SerializedName("profilePic")
                        private String profilePic;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
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
        }
    }
}
