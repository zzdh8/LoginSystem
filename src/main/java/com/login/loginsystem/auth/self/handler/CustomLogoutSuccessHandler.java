package com.login.loginsystem.auth.self.handler;

import com.login.loginsystem.auth.self.jwt.JwtBlackList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Duration;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private JwtBlackList jwtBlackList;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        String refreshToken = resolveRefreshToken(request);
        if (StringUtils.hasText(refreshToken)) {
            jwtBlackList.blacklistToken(refreshToken, Duration.ofMinutes(30)); //30분 동안 블랙리스트 추가
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
    private String resolveRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization");
        if (StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer ")) {
            return refreshToken.substring(7);
        }
        return null;
    }
}
