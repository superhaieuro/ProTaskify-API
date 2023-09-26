package com.protaskify.protaskify_api.service.auth;

import com.protaskify.protaskify_api.model.auth.AuthenticationRequest;
import com.protaskify.protaskify_api.model.auth.AuthenticationResponse;
import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.config.custom.CustomAuthenticationToken;
import com.protaskify.protaskify_api.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //Demo email but use student email instead of lecturer email
    private static final String LECTURER_EMAIL_COM = "@fpt.edu.vn";

    public AuthenticationResponse register(AuthenticationRequest request) {
        String jwtToken = null;
        if (request.getEmail().endsWith(LECTURER_EMAIL_COM)) {
            var user = Lecturer.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .email(request.getEmail())
                    .picture(request.getPicture())
                    .build();
            lecturerRepository.save(user);
            jwtToken = jwtService.generateToken(user);
        } else {
            var user = Student.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .email(request.getEmail())
                    .picture(request.getPicture())
                    .build();
            studentRepository.save(user);
            jwtToken = jwtService.generateToken(user);
        }
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new CustomAuthenticationToken(request.getId()));
        String jwtToken = null;
        String role = null;
        if (request.getEmail().endsWith(LECTURER_EMAIL_COM)) {
            var user = lecturerRepository.findAllById(request.getId());
            if (user.isEmpty()) {
                return register(request);
            } else {
                jwtToken = jwtService.generateToken(user.get());
                role = "LECTURER";
            }
        } else {
            var user = studentRepository.findAllById(request.getId());
            if (user.isEmpty()) {
                return register(request);
            } else {
                jwtToken = jwtService.generateToken(user.get());
                role = "STUDENT";
            }
        }
        return AuthenticationResponse.builder().token(jwtToken).role(role).build();
    }
}
