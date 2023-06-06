package test.shop.app.entities.orders;

import test.shop.app.entities.people.Customer;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "item_order")
    private List<OrderItem> orderItems;

    private Date invoiceDate;
    private Date payedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public boolean isPayed(){
        return getPayedDate()!=null;
    }

    public String getInvoiceDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(invoiceDate);
    }
    public String getPayedDateStr() {
        if(!isPayed()) return "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(payedDate);
    }
}
