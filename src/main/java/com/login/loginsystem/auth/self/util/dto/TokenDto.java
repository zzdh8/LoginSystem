package com.login.loginsystem.auth.self.util.dto;

import lombok.Builder;

@Builder
public record TokenDto(String grantType, String accessToken, String refreshToken) {}