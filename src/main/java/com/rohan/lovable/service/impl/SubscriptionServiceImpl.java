package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.subscription.CheckoutRequest;
import com.rohan.lovable.dto.subscription.CheckoutResponse;
import com.rohan.lovable.dto.subscription.PortalResponse;
import com.rohan.lovable.dto.subscription.SubscriptionResponse;
import com.rohan.lovable.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

}
