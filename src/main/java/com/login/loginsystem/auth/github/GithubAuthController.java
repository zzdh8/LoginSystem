package com.login.loginsystem.auth.github;

import com.login.loginsystem.auth.github.dto.GithubTokenResponse;
import com.login.loginsystem.common.dto.BaseResponse;
import com.login.loginsystem.common.exception.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GithubAuthController {
    private final GithubAuthService githubAuthService;

    @GetMapping("/login/oauth2/code/github")
    public BaseResponse<GithubTokenResponse> getGithubInfo(@RequestParam final String code) {
        return BaseResponse.success(SuccessCode.USER_LOGIN_SUCCESS, githubAuthService.getTokensInfo(code));
    }
}
