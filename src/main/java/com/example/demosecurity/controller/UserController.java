package com.example.demosecurity.controller;

import com.example.demosecurity.dto.AddUserRequest;
import com.example.demosecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        log.info("request: {}", request);
        userService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("logout");
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
