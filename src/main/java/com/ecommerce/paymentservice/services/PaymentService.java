package com.ecommerce.paymentservice.services;

import com.ecommerce.paymentservice.DTOs.InitiatePaymentRequestDTO;
import com.ecommerce.paymentservice.paymentgateway.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;


    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(InitiatePaymentRequestDTO initiatePaymentRequestDTO) throws StripeException {
        return paymentGateway.initiatePayment(initiatePaymentRequestDTO);
    }
}
