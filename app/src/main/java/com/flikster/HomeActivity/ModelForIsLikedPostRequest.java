package com.flikster.HomeActivity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhishek on 24-11-2017.
 */

public class ModelForIsLikedPostRequest {

    @SerializedName("statusCode")
    private Integer statusCode;


    @SerializedName("totalCount")
    private Integer totalCount;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @SerializedName("data")
    private InnerItems data;

    public InnerItems getData() {
        return data;
    }

    public void setData(InnerItems data) {
        this.data = data;
    }


    public class InnerItems {
        @SerializedName("Count")
        private String Count;
        @SerializedName("Items")
        private List<InnerMostItems> Items;

        public String getCount() {
            return Count;
        }

        public void setCount(String count) {
            Count = count;
        }

        public List<InnerMostItems> getItems() {
            return Items;
        }

        public void setItems(List<InnerMostItems> items) {
            Items = items;
        }
    }

    public class InnerMostItems {

        @SerializedName("type")
        private String type;
        @SerializedName("userId")
        private String userId;
        @SerializedName("entityId")
        private String entityId;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEntityId() {
            return entityId;
        }

        public void setEntityId(String entityId) {
            this.entityId = entityId;
        }
    }
}
