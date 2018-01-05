package com.flikster.HomeActivity.CommonFragments.AuctionFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 05-01-2018.
 */

public class AuctionPlaceBidData {
    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @SerializedName("bidAmount")
    private String bidAmount;

    @SerializedName("auctionId")
    private String auctionId;

    @SerializedName("userId")
    private String userId;

    public String getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(String bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AuctionPlaceBidData(String bidAmount, String auctionId, String userId) {
        this.bidAmount = bidAmount;
        this.auctionId = auctionId;
        this.userId = userId;
    }
}
