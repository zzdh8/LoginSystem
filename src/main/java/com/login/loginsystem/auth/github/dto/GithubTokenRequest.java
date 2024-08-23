package com.login.loginsystem.auth.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubTokenRequest(
        @JsonProperty("client_id") String clientId,
        @JsonProperty("client_secret") String clientSecret,
        @JsonProperty("code") String code
) {}