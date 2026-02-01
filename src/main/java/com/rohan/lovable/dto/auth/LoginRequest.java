package com.rohan.lovable.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
