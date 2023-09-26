package com.protaskify.protaskify_api.config.custom;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public CustomAuthenticationToken(Object principal) {
        super(principal, null);
    }
}