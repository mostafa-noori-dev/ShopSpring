package test.shop.app.controllers.api.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shop.app.entities.products.*;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.helper.uimodels.product.ProductVM;
import test.shop.app.services.products.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/getAll")
    public ServiceResponse<Product> getAllProducts(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        try {
            List<Product> result = service.getAllProducts(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @GetMapping("/getAll/{productCategoryId}")
    public ServiceResponse<ProductVM> getAllProductsByProductCategoryId(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @PathVariable long productCategoryId
    ) {
        try {
            List<ProductVM> result = service.getAllProductsByProductCategoryId(productCategoryId,pageSize,pageNumber);
            long totalCount=service.getAllCountByProductCategoryId(productCategoryId);
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }
    
    @GetMapping("")
    public ServiceResponse<Product> search(@RequestParam String keyword) {
        try {
            List<Product> result = service.search(keyword);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }

    @GetMapping("/find")
    public ServiceResponse<Product> getByCategoryId(@RequestParam long ProductCategoryId) {
        try {
            List<Product> result = service.findAllByCategory(ProductCategoryId);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }


    @GetMapping("/info/{id}")
    public ServiceResponse<ProductVM> getInfoProductById(@PathVariable long id) {
        try {
            Product result = service.getById(id);
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, new ProductVM(result));
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }

    @GetMapping("/newProducts")
    public ServiceResponse<ProductVM> newProducts() {
        try {
            List<ProductVM> result = service.findTop6ByOrderByAddDateDesc();
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }
    @GetMapping("/popularProducts")
    public ServiceResponse<ProductVM> popularProducts() {
        try {
            List<ProductVM> result = service.findTop6ByOrderByVisitCountDesc();
            return new ServiceResponse<ProductVM>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<ProductVM>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Product> add(@RequestBody ProductVM data) {
        try {
            Product result = service.add(data);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS,result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }
    @PutMapping("/")
    public ServiceResponse<Product> update(@RequestBody ProductVM data) {
        try {
            Product result = service.update(data);
            return new ServiceResponse<Product>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Product>(e);
        }
    }
    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }
}
