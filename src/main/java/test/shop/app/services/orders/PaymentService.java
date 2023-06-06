package test.shop.app.services.orders;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.shop.app.entities.orders.Invoice;
import test.shop.app.entities.orders.OrderItem;
import test.shop.app.entities.orders.Transactions;
import test.shop.app.entities.people.Customer;
import test.shop.app.entities.people.User;
import test.shop.app.entities.products.Product;
import test.shop.app.enums.PaymentType;
import test.shop.app.helper.payment.zarinpal.controllers.ZarinpalService;
import test.shop.app.helper.uimodels.orders.PaymentVM;
import test.shop.app.helper.uimodels.orders.StartPaymentVM;
import test.shop.app.helper.uimodels.orders.VerifyRequest;
import test.shop.app.services.people.CustomerService;
import test.shop.app.services.people.UserService;
import test.shop.app.services.products.ColorService;
import test.shop.app.services.products.ProductService;
import test.shop.app.services.products.SizeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ZarinpalService zarinpalService;

    @Autowired
    private TransactionsService transactionsService;

    public StartPaymentVM addPayment(PaymentVM data) throws Exception {
        //TODO: task must to do
        //1- insert user
        //2- insert customer
        //3- insert order item
        //4- insert invoice
        //5- redirect to bank
        StartPaymentVM response = new StartPaymentVM();
        List<OrderItem> orderItemList = new ArrayList<>();
        Customer customerInfo = null;
        if (data.getCustomerId() == 0) {
            User userInfo = userService.add(data.getCustomer().getUserInfo());
            Customer customer = data.getCustomer().getCustomerInfo();
            customer.setUser(userInfo);
            customerInfo = customerService.add(customer);
        } else {
            customerInfo = customerService.getById(data.getCustomerId());
        }
        Customer finalCustomerInfo = customerInfo;
        data.getOrderItems().forEach(x -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setColor(colorService.getById(x.getColorId()));
            orderItem.setCount(x.getCount());
            orderItem.setCustomer(finalCustomerInfo);
            Product product = productService.getById(x.getProductId());
            orderItem.setPrice(product.getPrice());
            orderItem.setProduct(product);
            orderItem.setSize(sizeService.getById(x.getSizeId()));
            orderItemService.add(orderItem);
            orderItemList.add(orderItem);
            response.setAmount(response.getAmount() + orderItem.getPrice());
        });
        Invoice invoice = new Invoice();
        invoice.setCustomer(customerInfo);
        invoice.setInvoiceDate(new Date());
        invoice.setPayedDate(null);
        invoice.setOrderItems(orderItemList);
        invoiceService.add(invoice);
        response.setDescription(data.getOrderItems().size() + " products for " + data.getCustomer().getFullName());
        response.setMobile(customerInfo.getMobile());
        response.setEmail(customerInfo.getEmail());
        response.setCustomer(customerInfo);
        response.setInvoice(invoice);
        response.setPaymentType(data.getPaymentType());
        return response;

    }

    public String goToPayment(StartPaymentVM startPaymentVM) throws Exception {
        String result = "#";
        if (startPaymentVM.getPaymentType() == PaymentType.ZarinPal) {
            return zarinpalService.goToPayment(startPaymentVM);
        }
        transactionsService.add(startPaymentVM);
        return result;
    }

    public Transactions doVerify(Transactions transactions) throws Exception {
        Transactions result = null;
        if (transactions.getPaymentType() == PaymentType.ZarinPal) {
            result = zarinpalService.goToVerify(transactions);
            if (result.getTransactionVerifyStatus() == 100) {
                Invoice invoice = invoiceService.getById(result.getInvoice().getId());
                invoice.setPayedDate(new Date());
                invoiceService.update(invoice);
            }
        }
        transactionsService.update(result);
        return result;
    }
}
