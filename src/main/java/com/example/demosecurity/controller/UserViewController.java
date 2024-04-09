package com.example.demosecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        log.info("signup");
        return "signup";
    }
}
