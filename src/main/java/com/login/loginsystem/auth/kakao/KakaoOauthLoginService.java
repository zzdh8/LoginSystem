package com.login.loginsystem.auth.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class KakaoOauthLoginService {

    @Value("${KAKAO_CLIENT_ID}")
    private String clientId;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final HttpHeaders httpHeaders = new HttpHeaders();

    public KakaoTokenResponse getTokensInfo(String code) {
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code"); //고정값
        params.add("client_id", clientId);
        params.add("redirect_uri", "http://localhost:8080/login/oauth2/code/kakao"); //등록한 redirect uri
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                httpEntity,
                KakaoTokenResponse.class
        );

        return response.getBody();
    }
}