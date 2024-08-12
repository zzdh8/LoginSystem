package com.login.loginsystem.join.dto;

import lombok.Builder;

@Builder
public record RequestJoinDto(
        String username,
        String password,
        String nickname
) {
}
