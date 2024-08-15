package com.login.loginsystem.join.service;

import com.login.loginsystem.member.domain.Authority;
import com.login.loginsystem.member.domain.Member;
import com.login.loginsystem.join.dto.RequestJoinDto;
import com.login.loginsystem.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*
     * 최소한의 Validation 적용
     * 1. 아이디 중복 검사
     * 2. 비밀번호 유효성 검사 (길이 15자, 영문, 숫자, 특수문자 포함)
     */

    //회원정보 저장
    public void join(RequestJoinDto requestJoinDto) {
        isNotDuplicatedUsername(requestJoinDto.username());
        isValidPassword(requestJoinDto.password());
        Member member = Member.builder()
                .username(requestJoinDto.username())
                .password(passwordEncoder.encode(requestJoinDto.password()))
                .nickname(requestJoinDto.nickname())
                .authority(Authority.ROLE_USER)
                .build();
        memberRepository.save(member);
    }

    //아이디 중복 검사
    private void isNotDuplicatedUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }

    private void isValidPassword(String password) {
        if (password.length() < 10 || !password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=-]).{8,15}$")) {
            throw new IllegalArgumentException("비밀번호는 8자 이상 15자 이하, 영문, 숫자, 특수문자를 포함해야 합니다.");
        }
    }
}
