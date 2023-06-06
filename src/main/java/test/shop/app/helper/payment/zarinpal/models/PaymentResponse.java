package test.shop.app.helper.payment.zarinpal.models;

import java.io.Serializable;

public class PaymentResponse implements Serializable {
    private String Authority;
    private long Status;

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        Authority = authority;
    }

    public long getStatus() {
        return Status;
    }

    public void setStatus(long status) {
        Status = status;
    }
}
