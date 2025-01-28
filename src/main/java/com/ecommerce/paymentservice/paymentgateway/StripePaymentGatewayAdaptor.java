package com.ecommerce.paymentservice.paymentgateway;

import com.ecommerce.paymentservice.DTOs.InitiatePaymentRequestDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentGatewayAdaptor implements PaymentGateway {
    @Value("${stripe.key}")
    private String apiKey;

    @Override
    public String initiatePayment(InitiatePaymentRequestDTO initiatePaymentRequestDTO){
         Stripe.apiKey=apiKey;

        // Creating the Price Object, here Price Object in Stripe means the Order that we have placed

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(1000L)
//                        .setRecurring(
//                                PriceCreateParams.Recurring.builder()
//                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
//                                        .build()
//                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("iPhone Charger").build()
                        )
                        .build();


        Price price=null;
        try {
            price = Price.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        // Now here we're setting the Price Object and Quantity of the Product to create the payment link
        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://example.com/success")
                                                        .build()
                                        ).build()
                        )
                        .build();
        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(paymentLinkCreateParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        return paymentLink.getUrl().toString();
    }
}
