package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.auth.AuthResponse;
import com.rohan.lovable.dto.auth.LoginRequest;
import com.rohan.lovable.dto.auth.SignupRequest;
import com.rohan.lovable.repository.UserRepository;
import com.rohan.lovable.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
