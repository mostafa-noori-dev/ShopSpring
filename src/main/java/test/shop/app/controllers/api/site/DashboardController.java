package test.shop.app.controllers.api.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.helper.uimodels.site.slider.DashboardVM;
import test.shop.app.services.orders.InvoiceService;
import test.shop.app.services.people.CustomerService;
import test.shop.app.services.people.UserService;
import test.shop.app.services.products.ProductCategoryService;
import test.shop.app.services.products.ProductService;
import test.shop.app.services.site.BlogService;
import test.shop.app.services.site.NavService;
import test.shop.app.services.site.SliderService;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private NavService navService;
    @Autowired
    private SliderService sliderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("")
    public ServiceResponse<DashboardVM> get() {
        try {
            DashboardVM result = new DashboardVM();
            result.setNavigations(navService.getAllCount());
            result.setSliders(sliderService.getAllCount());
            result.setActiveBlog(blogService.getAllCountData());
            result.setAllBlog(blogService.getAllCount());
            result.setCategories(productCategoryService.getAllCount());
            result.setProducts(productService.getAllCount());
            result.setExistsProducts(productService.getExistsCount());
            result.setEnableProducts(productService.getEnableCount());
            result.setUsers(userService.getAllCount());
            result.setUsers(userService.getEnableCount());
            result.setCustomers(customerService.getAllCount());
            result.setInvoices(invoiceService.getAllCount());
            result.setPayedInvoices(invoiceService.getPayedCount());
            return new ServiceResponse<DashboardVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<DashboardVM>(e);
        }
    }
}
