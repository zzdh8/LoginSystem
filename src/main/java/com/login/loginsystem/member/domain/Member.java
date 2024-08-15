package com.login.loginsystem.member.domain;

/*
 * 로그인 시스템 예제이므로 필요한 정보는
 * 아이디, 비밀번호, 이름, 닉네임에 한함.
 */

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String username; //아이디

    @Column(name = "user_password")
    private String password; //비밀번호

    @Column(name = "user_nickname")
    private String nickname; //닉네임

    @Enumerated(EnumType.STRING)
    private Authority authority;//권한
}
