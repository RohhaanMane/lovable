package com.rohan.lovable.service;

import com.rohan.lovable.dto.subscription.CheckoutRequest;
import com.rohan.lovable.dto.subscription.CheckoutResponse;
import com.rohan.lovable.dto.subscription.PortalResponse;
import com.rohan.lovable.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);


}
