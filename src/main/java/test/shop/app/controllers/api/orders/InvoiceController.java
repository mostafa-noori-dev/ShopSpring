package test.shop.app.controllers.api.orders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shop.app.config.JwtTokenUtil;
import test.shop.app.entities.orders.Invoice;
import test.shop.app.entities.people.Customer;
import test.shop.app.enums.UserRole;
import test.shop.app.helper.exceptions.JwtTokenException;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.helper.uimodels.people.UserVM;
import test.shop.app.services.orders.InvoiceService;
import test.shop.app.services.people.CustomerService;
import test.shop.app.services.people.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService service;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/find")
    public ServiceResponse<Invoice> find(@RequestParam long cid,
                                         @RequestParam Integer pageSize,
                                         @RequestParam Integer pageNumber,
                                         HttpServletRequest request) {
        try {
            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = customerService.getByUserId(userVM.getId());
                if (customer.getId() != cid)
                    throw new Exception("You can see only your invoices");
            }
            List<Invoice> result = service.findByCustomer(cid, pageSize, pageNumber);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }


    @GetMapping("/getInfo/{id}")
    public ServiceResponse<Invoice> search(@PathVariable long id, HttpServletRequest request) {
        try {
            Invoice result = service.getById(id);
            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = customerService.getByUserId(userVM.getId());
                if (customer.getId() != result.getCustomer().getId())
                    throw new Exception("You can see only your invoices");
                }
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Invoice> add(@RequestBody Invoice data) {
        try {
            Invoice result = service.add(data);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
        }
    }

    @PutMapping("/")
    public ServiceResponse<Invoice> update(@RequestBody Invoice data) {
        try {
            Invoice result = service.update(data);
            return new ServiceResponse<Invoice>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Invoice>(e);
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

    private UserVM getUserVMFromToken(HttpServletRequest request) throws JwtTokenException {
        String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
            throw new JwtTokenException("request token header does not set");
        String token = requestTokenHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        if (username == null)
            throw new JwtTokenException("username can not resolve");
        UserVM userVM = new UserVM(userService.getByUsername(username));
        return userVM;
    }
}
