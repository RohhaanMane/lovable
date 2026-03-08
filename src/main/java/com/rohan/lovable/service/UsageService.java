package com.rohan.lovable.service;

import com.rohan.lovable.dto.subscription.PlanLimitsResponse;
import com.rohan.lovable.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
