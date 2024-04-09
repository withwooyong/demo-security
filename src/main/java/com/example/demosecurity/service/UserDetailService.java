package com.example.demosecurity.service;

import com.example.demosecurity.domain.User;
import com.example.demosecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        log.info("username: {}", username);
        // email을 username으로 사용
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException(username));
        log.info("user: {}", user);
        return user;
    }
}

