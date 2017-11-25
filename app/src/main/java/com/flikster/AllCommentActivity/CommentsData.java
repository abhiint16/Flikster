package com.flikster.AllCommentActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 25-11-2017.
 */

public class CommentsData {
    @SerializedName("hits")
    private CommentsInnerData hits;

    public CommentsInnerData getHits() {
        return hits;
    }

    public void setHits(CommentsInnerData hits) {
        this.hits = hits;
    }

    public class CommentsInnerData
    {
        @SerializedName("total")
        private String total;
        @SerializedName("hits")
        private List<CommentsInnerInnerData> hits;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<CommentsInnerInnerData> getHits() {
            return hits;
        }

        public void setHits(List<CommentsInnerInnerData> hits) {
            this.hits = hits;
        }


        public  class CommentsInnerInnerData
        {
            @SerializedName("_source")
            private CommentInnerMostData _source;

            public CommentInnerMostData get_source() {
                return _source;
            }

            public void set_source(CommentInnerMostData _source) {
                this._source = _source;
            }

            public class CommentInnerMostData
            {
                @SerializedName("userName")
                private String userName;
                @SerializedName("commentText")
                private String commentText;
                @SerializedName("createdAt")
                private String createdAt;

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getCommentText() {
                    return commentText;
                }

                public void setCommentText(String commentText) {
                    this.commentText = commentText;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }
            }
        }

    }
}
