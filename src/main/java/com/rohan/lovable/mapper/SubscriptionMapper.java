package com.rohan.lovable.mapper;

import com.rohan.lovable.dto.subscription.SubscriptionResponse;
import com.rohan.lovable.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionResponse toSubscriptionResponse(Subscription subscription);
}
