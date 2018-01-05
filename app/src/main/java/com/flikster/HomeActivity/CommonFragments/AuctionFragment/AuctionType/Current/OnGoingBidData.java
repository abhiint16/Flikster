package com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Logins on 04-01-2018.
 */

public class OnGoingBidData {
    @SerializedName("statusCode")
    public Integer statusCode;
    @SerializedName("highestBid")
    public Integer highestBid;
    @SerializedName("bidCount")
    public Integer bidCount;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Integer highestBid) {
        this.highestBid = highestBid;
    }

    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }
}
