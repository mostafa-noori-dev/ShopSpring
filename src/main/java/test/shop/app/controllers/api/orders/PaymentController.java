package test.shop.app.controllers.api.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.shop.app.helper.payment.zarinpal.controllers.ZarinpalService;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.helper.uimodels.orders.PaymentVM;
import test.shop.app.helper.uimodels.orders.StartPaymentVM;
import test.shop.app.services.orders.PaymentService;
import test.shop.app.services.orders.TransactionsService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PostMapping("/")
    public ServiceResponse<StartPaymentVM> addPayment(@RequestBody PaymentVM data){
        try{
            StartPaymentVM startPaymentVM =service.addPayment(data);
            String location = service.goToPayment(startPaymentVM);
            startPaymentVM.setLocation(location);
            return new ServiceResponse<StartPaymentVM>(ResponseStatus.SUCCESS, startPaymentVM);
        }catch (Exception e){
            return new ServiceResponse<StartPaymentVM>(e);
        }
    }
}
