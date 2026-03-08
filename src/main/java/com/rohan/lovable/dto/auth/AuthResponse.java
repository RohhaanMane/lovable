package com.rohan.lovable.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {
}
