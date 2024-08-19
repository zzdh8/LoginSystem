package com.login.loginsystem.auth.self.util;

import com.login.loginsystem.auth.self.util.dto.AuthReqDto;
import com.login.loginsystem.auth.self.util.dto.TokenDto;
import com.login.loginsystem.auth.self.service.AuthService;
import com.login.loginsystem.common.dto.BaseResponse;
import com.login.loginsystem.common.exception.SuccessCode;
import com.login.loginsystem.common.exception.model.RefreshTokenInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<TokenDto> login(@RequestBody AuthReqDto authReqDto){
        return BaseResponse.success(SuccessCode.USER_LOGIN_SUCCESS, authService.login(authReqDto));
    }

    @PostMapping("/refresh")
    public BaseResponse<TokenDto> refresh(HttpServletRequest request) throws RefreshTokenInvalidException {
        return BaseResponse.success(SuccessCode.USER_LOGIN_SUCCESS, authService.refresh(request));
    }
}
