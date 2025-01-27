package com.ecommerce.paymentservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDTO {
    private Long orderId;
    private String phoneNumber;
}
