package test.shop.app.controllers.api.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shop.app.config.JwtTokenUtil;
import test.shop.app.entities.people.Customer;
import test.shop.app.entities.people.User;
import test.shop.app.enums.UserRole;
import test.shop.app.helper.exceptions.JwtTokenException;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.helper.uimodels.people.CustomerVM;
import test.shop.app.helper.uimodels.people.UserVM;
import test.shop.app.services.people.CustomerService;
import test.shop.app.services.people.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;


    @GetMapping("/getAll")
    public ServiceResponse<Customer> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        try {
            List<Customer> result = service.getAll(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Customer>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Customer> search(@PathVariable long id) {
        try{
            Customer result=service.getById(id);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Customer>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Customer> add(@RequestBody Customer data){
        try{
            Customer result=service.add(data);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Customer>(e);
        }
    }
    @PutMapping("/")
    public ServiceResponse<Customer> update(@RequestBody Customer data){
        try{
            Customer result=service.update(data);
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Customer>(e);
        }
    }
    @PutMapping("/updateInfo")
    public ServiceResponse<Customer> updateInfo(@RequestBody CustomerVM data, HttpServletRequest request){
        try{
            UserVM userVM = getUserVMFromToken(request);
            if (userVM.getRole() != UserRole.ADMIN) {
                Customer customer = service.getByUserId(userVM.getId());
                if (customer.getId() != data.getId())
                    throw new Exception("You can only update your information");
            }
            Customer result=service.update(data.getCustomerInfo());
            userService.update(data.getUserInfo());
            return new ServiceResponse<Customer>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Customer>(e);
        }
    }
    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try{
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
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
