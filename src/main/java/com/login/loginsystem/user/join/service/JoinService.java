package com.login.loginsystem.user.join.service;

import com.login.loginsystem.user.User;
import com.login.loginsystem.user.join.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;

    public void join(User user) {
        userRepository.save(user);
    }
}
