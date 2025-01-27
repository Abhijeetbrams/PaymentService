package com.ecommerce.paymentservice.controllers;

import com.ecommerce.paymentservice.DTOs.InitiatePaymentRequestDTO;
import com.ecommerce.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDTO initiatePaymentRequestDTO) throws StripeException {
        return paymentService.initiatePayment(initiatePaymentRequestDTO);
    }
}
