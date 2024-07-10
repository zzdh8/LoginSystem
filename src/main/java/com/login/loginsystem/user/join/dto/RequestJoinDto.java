package com.login.loginsystem.user.join.dto;

public record RequestJoinDto(
        String username,
        String password,
        String nickname
) {
}
