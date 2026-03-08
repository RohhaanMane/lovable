package com.rohan.lovable.service.impl;

import com.rohan.lovable.dto.auth.AuthResponse;
import com.rohan.lovable.dto.auth.LoginRequest;
import com.rohan.lovable.dto.auth.SignupRequest;
import com.rohan.lovable.entity.User;
import com.rohan.lovable.error.BadRequestException;
import com.rohan.lovable.mapper.UserMapper;
import com.rohan.lovable.repository.UserRepository;
import com.rohan.lovable.security.AuthUtil;
import com.rohan.lovable.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already in exists with username: " + request.username());
        });

        User user =  userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token, userMapper.toUserProfileResponse(user));
    }
}
