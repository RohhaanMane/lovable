package com.rohan.lovable.service;

import com.rohan.lovable.dto.auth.AuthResponse;
import com.rohan.lovable.dto.auth.LoginRequest;
import com.rohan.lovable.dto.auth.SignupRequest;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
