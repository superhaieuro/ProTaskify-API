package com.protaskify.protaskify_api.config;

import com.protaskify.protaskify_api.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests().requestMatchers("/api/v1/auth/**", "/api/v1/common/**", "/web-socket/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/student/**").hasAuthority("STUDENT")
                .and()
                .authorizeHttpRequests().requestMatchers("/api/v1/lecturer/**").hasAuthority("LECTURER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
//        return http
//                .csrf().disable()
//                .cors().and().authorizeHttpRequests().anyRequest().permitAll().and().build();
    }
}
