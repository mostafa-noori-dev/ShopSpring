package test.shop.app.helper.uimodels.orders;

import test.shop.app.enums.PaymentType;
import test.shop.app.helper.uimodels.people.CustomerVM;

import java.util.List;

public class PaymentVM {
    private CustomerVM customer;
    private List<OrderItemVM> orderItems;
    private PaymentType paymentType;
    private long customerId;

    public CustomerVM getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVM customer) {
        this.customer = customer;
    }

    public List<OrderItemVM> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemVM> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
