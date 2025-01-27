package com.ecommerce.paymentservice.paymentgateway;

import com.ecommerce.paymentservice.DTOs.InitiatePaymentRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class RazorPayPaymentGatewayAdaptor implements PaymentGateway {
    @Override
    public String initiatePayment(InitiatePaymentRequestDTO initiatePaymentRequestDTO) {
        return "Payment initiated successfully";
    }
}
