package com.rohan.lovable.service;

import com.rohan.lovable.dto.subscription.CheckoutRequest;
import com.rohan.lovable.dto.subscription.CheckoutResponse;
import com.rohan.lovable.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;

import java.util.Map;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal();

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
