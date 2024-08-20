package com.login.loginsystem.auth.kakao;

import com.login.loginsystem.common.dto.BaseResponse;
import com.login.loginsystem.common.exception.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoOauthLoginController {
    private final KakaoOauthLoginService kakaoOauthLoginService;

    @GetMapping("/login/oauth2/code/kakao")
    public BaseResponse<KakaoTokenResponse> kakaologin(@RequestParam final String code) {
        return BaseResponse.success(SuccessCode.USER_LOGIN_SUCCESS, kakaoOauthLoginService.getTokensInfo(code));
    }
}
