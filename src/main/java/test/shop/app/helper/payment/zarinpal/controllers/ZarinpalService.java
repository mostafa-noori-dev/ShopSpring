package test.shop.app.helper.payment.zarinpal.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.shop.app.entities.orders.Transactions;
import test.shop.app.helper.payment.zarinpal.models.PaymentRequest;
import test.shop.app.helper.payment.zarinpal.models.PaymentResponse;
import test.shop.app.helper.uimodels.orders.StartPaymentVM;
import test.shop.app.helper.uimodels.orders.VerifyRequest;
import test.shop.app.helper.uimodels.orders.VerifyResponse;
import test.shop.app.helper.utils.HttpUtils;
@Service
public class ZarinpalService {

    @Value("${zarinpal.merchantId}")
    private String merchantId;

    @Value("${zarinpal.callBackUrl}")
    private String callBackUrl;

    @Value("${zarinpal.paymentAddress}")
    private String paymentAddress;

    @Value("${zarinpal.startPayAddress}")
    private String startPayAddress;

    @Value("${zarinpal.verifactionAddress}")
    private String verifactionAddress;

    public String goToPayment(StartPaymentVM data) throws Exception {
        PaymentRequest request=new PaymentRequest();
        request.setAmount(data.getAmount());
        request.setDescription(data.getDescription());
        request.setCallbackUrl(callBackUrl);
        request.setMerchantID(merchantId);
        request.setEmail(data.getEmail());
        request.setMobile(data.getMobile());
        HttpUtils<PaymentResponse> httpUtils=new HttpUtils<>(PaymentResponse.class);
        PaymentResponse response = httpUtils.callPost(paymentAddress,request );
        data.setStatus(response.getStatus());
        data.setAuthority(response.getAuthority());
        if(response.getStatus()!=100)
            throw new Exception("Error on payment");
        return startPayAddress+response.getAuthority();
    }


    public Transactions goToVerify(Transactions transactions) throws Exception {
        VerifyRequest request=new VerifyRequest();
        request.setAmount(transactions.getAmount());
        request.setAuthority(transactions.getAuthority());
        request.setMerchantID(merchantId);
        HttpUtils<VerifyResponse> httpUtils=new HttpUtils<>(VerifyResponse.class);
        VerifyResponse response = httpUtils.callPost(verifactionAddress,request );
        transactions.setTransactionVerifyStatus(response.getStatus());
        transactions.setRefId(response.getRefID());
        if(response.getStatus()!=100)
            throw new Exception("Error on payment");
        return transactions;
    }
}
