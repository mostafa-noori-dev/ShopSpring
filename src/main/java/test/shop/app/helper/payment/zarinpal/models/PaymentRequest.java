package test.shop.app.helper.payment.zarinpal.models;

import java.io.Serializable;

public class PaymentRequest implements Serializable {
    private String MerchantID;
    private long amount;
    private String CallbackUrl;
    private String Description;
    private String Email;
    private String Mobile;
    public String getMerchantID() {
        return MerchantID;
    }

    public void setMerchantID(String merchant_id) {
        this.MerchantID = merchant_id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCallbackUrl() {
        return CallbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        CallbackUrl = callbackUrl;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
