package com.login.loginsystem.auth.github;

import com.login.loginsystem.auth.github.dto.GithubTokenRequest;
import com.login.loginsystem.auth.github.dto.GithubTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubAuthService {
    private static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${GITHUB_CLIENT_ID}")
    private String clientId;

    @Value("${GITHUB_CLIENT_SECRET}")
    private String clientSecret;


    public GithubTokenResponse getTokensInfo(String code) {
        return restTemplate.postForObject(ACCESS_TOKEN_URL, new GithubTokenRequest(clientId, clientSecret, code), GithubTokenResponse.class);
    }
}
