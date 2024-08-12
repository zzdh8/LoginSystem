package com.login.loginsystem.join.service;

import com.login.loginsystem.user.domain.User;
import com.login.loginsystem.join.dto.RequestJoinDto;
import com.login.loginsystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*
     * 최소한의 Validation 적용
     * 1. 아이디 중복 검사
     * 2. 비밀번호 유효성 검사 (길이 15자, 영문, 숫자, 특수문자 포함)
     */

    //회원정보 저장
    public void join(RequestJoinDto requestJoinDto) {
        if (isNotDuplicatedUsername(requestJoinDto.username())) {
            if (isValidPassword(requestJoinDto.password())) {
                User user = User.builder()
                        .username(requestJoinDto.username())
                        .password(passwordEncoder.encode(requestJoinDto.password()))
                        .nickname(requestJoinDto.nickname())
                        .build();
                userRepository.save(user);
            }
        }
    }

    //아이디 중복 검사
    private boolean isNotDuplicatedUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        } else {
            return true;
        }
    }

    private boolean isValidPassword(String password) {
        if(password.length() > 10 && password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=-]).{8,15}$")){
            return true;
        } else {
            throw new IllegalArgumentException("비밀번호는 8자 이상 15자 이하, 영문, 숫자, 특수문자를 포함해야 합니다.");
        }
    }
}
