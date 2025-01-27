package com.ecommerce.paymentservice.paymentgateway;

import com.ecommerce.paymentservice.DTOs.InitiatePaymentRequestDTO;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String initiatePayment(InitiatePaymentRequestDTO initiatePaymentRequestDTO) throws StripeException;
}
