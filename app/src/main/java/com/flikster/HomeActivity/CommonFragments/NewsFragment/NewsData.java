package com.flikster.HomeActivity.CommonFragments.NewsFragment;

import com.flikster.HomeActivity.FeedInnerData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 15-11-2017.
 */

public class NewsData {

    @SerializedName("hits")
    private NewsInnerData hits;

    public NewsInnerData getHits() {
        return hits;
    }

    public void setHits(NewsInnerData hits) {
        this.hits = hits;
    }

    public class NewsInnerData {
        @SerializedName("total")
        private Integer total;
        @SerializedName("hits")
        private List<NewsInnerMoreData> hits;

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<NewsInnerMoreData> getHits() {
            return hits;
        }

        public void setHits(List<NewsInnerMoreData> hits) {
            this.hits = hits;
        }
    }

    public class NewsInnerMoreData
    {
        @SerializedName("_source")
        private NewsInnerMostData _source;

        public NewsInnerMostData get_source() {
            return _source;
        }

        public void set_source(NewsInnerMostData _source) {
            this._source = _source;
        }
    }

    public class NewsInnerMostData
    {
        @SerializedName("profilePic")
        private String profilePic;
        @SerializedName("title")
        private String title;

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


}
