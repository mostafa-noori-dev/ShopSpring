package test.shop.app.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import test.shop.app.entities.orders.Transactions;
import test.shop.app.helper.exceptions.DataNotFoundException;
import test.shop.app.services.orders.PaymentService;
import test.shop.app.services.orders.TransactionsService;
import test.shop.app.services.products.ProductService;
import test.shop.app.services.site.BlogService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable long id, Model model) {
        model.addAttribute("dataId", id);
        try {
            blogService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "blogInfo";
    }

    @GetMapping("/products/{id}")
    public String productsCategory(@PathVariable long id, Model model) {
        model.addAttribute("dataId", id);
        return "productsCategory";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable long id, Model model) {
        model.addAttribute("dataId", id);
        try {
            productService.increaseVisitCount(id);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return "product";
    }

    @GetMapping("/basket")
    public String basket() {
        return "basket";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/verify")
    public String verify(@RequestParam String Authority, @RequestParam String Status, Model model) {
        try {
            Transactions transactions = transactionsService.getByAuthority(Authority);
            if(transactions==null)
                throw new Exception("Data Not Found");
            transactions.setVerifyStatus(Status);
            if ((transactions.getRefId() == null || transactions.getRefId().equals("")) && Status.toLowerCase().equals("OK".toLowerCase()))
            {
                Transactions result = paymentService.doVerify(transactions);
                model.addAttribute("transaction", result);
            }
            else{
                model.addAttribute("transaction", transactions);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(e!=null && e.getMessage()!=null)
                model.addAttribute("Message", e.getMessage());
        }
        return "verify";
    }
}
