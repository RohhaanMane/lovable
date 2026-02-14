package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.subscription.PlanLimitsResponse;
import com.rohan.lovable.dto.subscription.UsageTodayResponse;
import com.rohan.lovable.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
