package com.example.demosecurity.service;

import com.example.demosecurity.domain.User;
import com.example.demosecurity.dto.AddUserRequest;
import com.example.demosecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        log.info("request: {}", dto);
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .auth(dto.getAuth())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
