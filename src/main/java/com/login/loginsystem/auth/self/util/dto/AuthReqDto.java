package com.login.loginsystem.auth.self.util.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthReqDto {
    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
