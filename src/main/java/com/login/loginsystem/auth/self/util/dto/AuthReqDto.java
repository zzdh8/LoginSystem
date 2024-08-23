package com.login.loginsystem.auth.self.util.dto;

import lombok.Builder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Builder
public record AuthReqDto(String username, String password) {
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}