package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.auth.UserProfileResponse;
import com.rohan.lovable.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
