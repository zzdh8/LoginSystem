package com.login.loginsystem.auth.self.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    static final String AUTHORIZATION_HEADER = "Authorization";
    static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;
    private final JwtBlackList jwtBlackList;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String jwt = resolveToken(request);
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            if (!request.getRequestURI().equals("/member/refresh")) {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}