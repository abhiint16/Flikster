package com.flikster.HomeActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 01-12-2017.
 */

public class ShopByVideoData {
    @SerializedName("hits")
    private ShopByVideoInnerData hits;

    public ShopByVideoInnerData getHits() {
        return hits;
    }

    public void setHits(ShopByVideoInnerData hits) {
        this.hits = hits;
    }

    public class ShopByVideoInnerData
    {
        @SerializedName("total")
        private Integer total;
        @SerializedName("hits")
        private List<ShopByVideoInnerInnerData> hits;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<ShopByVideoInnerInnerData> getHits() {
            return hits;
        }

        public void setHits(List<ShopByVideoInnerInnerData> hits) {
            this.hits = hits;
        }

        public class ShopByVideoInnerInnerData
        {
            @SerializedName("_source")
            private ShopByVideoInnerMostData _source;

            public ShopByVideoInnerMostData get_source() {
                return _source;
            }

            public void set_source(ShopByVideoInnerMostData _source) {
                this._source = _source;
            }

            public class ShopByVideoInnerMostData
            {
                @SerializedName("videoUrl")
                private String videoUrl;
                @SerializedName("title")
                private String title;
                @SerializedName("thumbnail")
                private String thumbnail;

                public String getVideoUrl() {
                    return videoUrl;
                }

                public void setVideoUrl(String videoUrl) {
                    this.videoUrl = videoUrl;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(String thumbnail) {
                    this.thumbnail = thumbnail;
                }
            }
        }
    }

}
