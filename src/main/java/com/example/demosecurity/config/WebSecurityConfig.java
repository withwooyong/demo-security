package com.example.demosecurity.config;

import com.example.demosecurity.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userDetailService;

//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----------------------filterChain-------------------------");
        return http // 인증, 인가 설정
                .authorizeHttpRequests(x -> {
                    x.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            .requestMatchers("/login", "/signup", "/user").permitAll()
                            .anyRequest().authenticated();
                }) // 폼 기반 로그인 설정
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/articles")
                ) // 로그아웃 설정
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
                .cors(AbstractHttpConfigurer::disable) // CORS 비활성화
                .build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        log.info("----------------------authenticationManager-------------------------");
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        log.info("----------------------bCryptPasswordEncoder-------------------------");
        return new BCryptPasswordEncoder();
    }
}
