package test.shop.app.helper.uimodels.orders;

import test.shop.app.entities.orders.OrderItem;
import test.shop.app.entities.people.Customer;
import test.shop.app.entities.products.Color;
import test.shop.app.entities.products.Product;
import test.shop.app.entities.products.Size;

public class OrderItemVM {
    private long id;
    private long productId;
    private long customerId;
    private long count;
    private long price;
    private long colorId;
    private long sizeId;

    public OrderItemVM() {
    }

    public OrderItemVM(OrderItem item) {
        setId(item.getId());
        setProductId(item.getProduct().getId());
        setCustomerId(item.getCustomer().getId());
        setCount(item.getCount());
        setPrice(item.getPrice());
        setColorId(item.getColor().getId());
        setSizeId(item.getSize().getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getColorId() {
        return colorId;
    }

    public void setColorId(long colorId) {
        this.colorId = colorId;
    }

    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }
}
