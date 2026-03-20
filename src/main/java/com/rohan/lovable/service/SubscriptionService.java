package com.rohan.lovable.service;

import com.rohan.lovable.dto.subscription.CheckoutRequest;
import com.rohan.lovable.dto.subscription.CheckoutResponse;
import com.rohan.lovable.dto.subscription.PortalResponse;
import com.rohan.lovable.dto.subscription.SubscriptionResponse;
import com.rohan.lovable.enums.SubscriptionStatus;

import java.time.Instant;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription();

    void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);

    void updateSubscription(String id, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId);

    void cancelSubscription(String id);

    void renewSubscriptionPeriod(String gatewaySubscriptionId, Instant periodStart, Instant periodEnd);

    void markSubscriptionPastDue(String gatewaySubscriptionId);
}
