package com.login.loginsystem.join.util;

import com.login.loginsystem.common.dto.BaseResponse;
import com.login.loginsystem.common.exception.SuccessCode;
import com.login.loginsystem.join.dto.RequestJoinDto;
import com.login.loginsystem.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JoinController {
    private final JoinService joinService;

    //회원가입
    @PostMapping("/join")
    public BaseResponse<String> join(@RequestBody RequestJoinDto requestJoinDto) {
        joinService.join(requestJoinDto);
        return BaseResponse.success(SuccessCode.USER_JOIN_SUCCESS);
    }
}
