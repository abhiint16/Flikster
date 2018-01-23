package com.flikster.CheckoutActivity.CheckoutFragment;

import com.flikster.HomeActivity.GlobalSearchGetData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Logins on 22-01-2018.
 */

public class PaymentRequest {


    @SerializedName("reqData")
    private PaymentRequestInnerdata reqData;


    @SerializedName("payment_request")
    private PaymentRequestSuccesdata payment_request;

    public PaymentRequestSuccesdata getPayment_request() {
        return payment_request;
    }

    public void setPayment_request(PaymentRequestSuccesdata payment_request) {
        this.payment_request = payment_request;
    }

    //

    @SerializedName("success")
    private boolean success;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public PaymentRequest(PaymentRequestInnerdata reqData) {
        this.reqData = reqData;
    }

    public static class PaymentRequestInnerdata {
        @SerializedName("orderId")
        private String orderId;
        @SerializedName("phone")
        private String email;
        @SerializedName("email")
        private String phone;
        @SerializedName("buyer")
        private String buyer;
        @SerializedName("buyer_name")
        private String buyer_name;
        @SerializedName("amount")
        private String amount;
        @SerializedName("purpose")
        private String purpose;
        @SerializedName("status")
        private String status;
        @SerializedName("send_sms")
        private String send_sms;
        @SerializedName("send_email")
        private String send_email;
        @SerializedName("sms_status")
        private String sms_status;
        @SerializedName("email_status")
        private String email_status;
        @SerializedName("shorturl")
        private String shorturl;
        @SerializedName("redirect_url")
        private String redirect_url;
        @SerializedName("webhook")
        private String webhook;
        @SerializedName("allow_repeated_payments")
        private String allow_repeated_payments;

        public PaymentRequestInnerdata(String orderId, String email, String phone,
                                       String buyer, String buyer_name, String amount, String purpose, String status, String send_sms, String send_email, String sms_status, String email_status, String shorturl, String redirect_url, String webhook, String allow_repeated_payments) {
            this.orderId = orderId;
            this.email = email;
            this.phone = phone;
            this.buyer = buyer;
            this.buyer_name = buyer_name;
            this.amount = amount;
            this.purpose = purpose;
            this.status = status;
            this.send_sms = send_sms;
            this.send_email = send_email;
            this.sms_status = sms_status;
            this.email_status = email_status;
            this.shorturl = shorturl;
            this.redirect_url = redirect_url;
            this.webhook = webhook;
            this.allow_repeated_payments = allow_repeated_payments;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBuyer() {
            return buyer;
        }

        public void setBuyer(String buyer) {
            this.buyer = buyer;
        }

        public String getBuyer_name() {
            return buyer_name;
        }

        public void setBuyer_name(String buyer_name) {
            this.buyer_name = buyer_name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSend_sms() {
            return send_sms;
        }

        public void setSend_sms(String send_sms) {
            this.send_sms = send_sms;
        }

        public String getSend_email() {
            return send_email;
        }

        public void setSend_email(String send_email) {
            this.send_email = send_email;
        }

        public String getSms_status() {
            return sms_status;
        }

        public void setSms_status(String sms_status) {
            this.sms_status = sms_status;
        }

        public String getEmail_status() {
            return email_status;
        }

        public void setEmail_status(String email_status) {
            this.email_status = email_status;
        }

        public String getShorturl() {
            return shorturl;
        }

        public void setShorturl(String shorturl) {
            this.shorturl = shorturl;
        }

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }

        public String getWebhook() {
            return webhook;
        }

        public void setWebhook(String webhook) {
            this.webhook = webhook;
        }

        public String getAllow_repeated_payments() {
            return allow_repeated_payments;
        }

        public void setAllow_repeated_payments(String allow_repeated_payments) {
            this.allow_repeated_payments = allow_repeated_payments;
        }
    }

    public static class PaymentRequestSuccesdata {
        @SerializedName("longurl")
        private String longurl;

        @SerializedName("redirect_url")
        private String redirect_url;

        public String getLongurl() {
            return longurl;
        }

        public void setLongurl(String longurl) {
            this.longurl = longurl;
        }

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }
    }
}
