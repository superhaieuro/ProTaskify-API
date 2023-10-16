package com.protaskify.protaskify_api.config;

import com.protaskify.protaskify_api.config.custom.CustomAuthenticationProvider;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return userEmail -> {
            Optional<Student> student = studentRepository.findAllByEmail(userEmail);
            if (student.isPresent()) {
                return student.get();
            } else {
                Optional<Lecturer> lecturer = lecturerRepository.findAllByEmail(userEmail);
                if (lecturer.isPresent()) {
                    return lecturer.get();
                } else {
                    throw new UsernameNotFoundException("User not found");
                }
            }
        };
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
