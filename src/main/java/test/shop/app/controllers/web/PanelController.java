package test.shop.app.controllers.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import test.shop.app.config.JwtTokenUtil;
import test.shop.app.entities.people.Customer;
import test.shop.app.entities.people.User;
import test.shop.app.enums.UserRole;
import test.shop.app.helper.uimodels.people.UserVM;
import test.shop.app.services.people.CustomerService;
import test.shop.app.services.people.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequestMapping("/panel")
public class PanelController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String index(HttpServletRequest request, Model model){
        if(request.getCookies()==null)
            return "login";
        Optional<Cookie> userTokenCookieOptional = Arrays.stream(request.getCookies()).filter(x -> x.getName().toLowerCase().equals("userToken".toLowerCase())).findFirst();
        if(!userTokenCookieOptional.isPresent())
            return "login";
        Cookie userTokenCookie=userTokenCookieOptional.get();
        if(userTokenCookie==null)
            return "login";
        String userToken = userTokenCookie.getValue();
        String username = jwtTokenUtil.getUsernameFromToken(userToken);
        UserVM user = new UserVM(userService.getByUsername(username));
        if (user.getRole() != UserRole.ADMIN) {
            Customer customer = customerService.getByUserId(user.getId());
            user.setCustomerId(customer.getId());
        }
        model.addAttribute("user", user);
        return "panel";
    }
}
