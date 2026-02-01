package com.rohan.lovable.dto.auth;

public record SignupRequest(
        String email,
        String name,
        String password
) {

}
