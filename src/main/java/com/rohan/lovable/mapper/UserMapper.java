package com.rohan.lovable.mapper;

import com.rohan.lovable.dto.auth.SignupRequest;
import com.rohan.lovable.dto.auth.UserProfileResponse;
import com.rohan.lovable.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);
}
