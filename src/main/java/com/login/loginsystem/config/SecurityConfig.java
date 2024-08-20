package com.login.loginsystem.config;

import com.login.loginsystem.auth.self.handler.CustomLogoutSuccessHandler;
import com.login.loginsystem.auth.self.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends Exception {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtBlackList jwtBlackList;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> {
                            CorsConfigurationSource source = request -> {
                                var cors = new CorsConfiguration();
                                cors.addAllowedOriginPattern("*");
                                cors.setAllowedMethods(List.of("*"));
                                cors.setAllowedHeaders(List.of("*"));
                                cors.setAllowCredentials(true);
                                cors.setMaxAge(3600L);
                                return cors;
                            };
                            c.configurationSource(source);
                        }
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/**").permitAll()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtFilter(tokenProvider, jwtBlackList), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        (exception) -> exception
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .logout((logout) -> logout
                        .logoutUrl("/api/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                );

        return http.build();
    }
}