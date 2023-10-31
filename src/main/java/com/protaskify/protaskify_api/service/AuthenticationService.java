package com.protaskify.protaskify_api.service;

import com.google.gson.JsonObject;
import com.protaskify.protaskify_api.model.enity.Admin;
import com.protaskify.protaskify_api.model.response.AuthenticationResponse;
import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.AdminRepository;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.config.custom.CustomAuthenticationToken;
import com.protaskify.protaskify_api.config.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;

    //Demo email but use normal email instead of lecturer email
    private static final String LECTURER_EMAIL_COM = "@gmail.com";

    //Decode token to get user information
    public JsonObject getUserInfo(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        byte[] decodedBytes = decoder.decode(token);
        String decodedToken = new String(decodedBytes, StandardCharsets.UTF_8);
        com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
        com.google.gson.JsonObject jsonObject = parser.parse(decodedToken).getAsJsonObject();
        return jsonObject;
    }

    public AuthenticationResponse authenticate(String token) {
        token = token.split("\\.")[1];
        JsonObject userData = getUserInfo(token);
        String userEmail = userData.get("email").getAsString();
        authenticationManager.authenticate(new CustomAuthenticationToken(userEmail));
        String jwtToken = null;
        String role = null;
        AuthenticationResponse authenticationResponse = null;
        var admin = adminRepository.findAllByEmail(userEmail);
        if (admin.isPresent()) {
            admin.get().setPicture(userData.get("picture").getAsString());
            adminRepository.save(admin.get());
            authenticationResponse = AuthenticationResponse.builder()
                    .token(jwtService.generateToken(admin.get()))
                    .userInfo(admin)
                    .build();
        } else {
            if (userEmail.endsWith(LECTURER_EMAIL_COM)) {
                var user = lecturerRepository.findAllByEmail(userEmail);
                if (user.isPresent()) {
                    //Update picture
                    user.get().setPicture(userData.get("picture").getAsString());
                    lecturerRepository.save(user.get());
                    authenticationResponse = AuthenticationResponse.builder()
                            .token(jwtService.generateToken(user.get()))
                            .userInfo(user
//                                Lecturer.builder()
//                                        .id(user.get().getId())
//                                        .name(user.get().getName())
//                                        .email(user.get().getEmail())
//                                        .picture(userData.get("picture").getAsString())
//                                        .build()
                            )
                            .build();
                }
            } else {
                var user = studentRepository.findAllByEmail(userEmail);
                if (user.isPresent()) {
                    //Update picture
                    user.get().setPicture(userData.get("picture").getAsString());
                    user.get().setStatus(true);
                    studentRepository.save(user.get());
                    authenticationResponse = AuthenticationResponse.builder()
                            .token(jwtService.generateToken(user.get()))
                            .userInfo(user
//                                Student.builder()
//                                        .id(user.get().getId())
//                                        .name(user.get().getName())
//                                        .email(user.get().getEmail())
//                                        .picture(userData.get("picture").getAsString())
//                                        .build()
                            )
                            .isInGroup(user.get().getGroup() != null && user.get().getGroup().isStatus())
                            .build();
                }
            }
        }
        return authenticationResponse;
    }

//    public AuthenticationResponse authenticate(String token) {
//        token = token.split("\\.")[1];
//        String userEmail = getUserInfo(token).get("email").getAsString();
//        authenticationManager.authenticate(new CustomAuthenticationToken(userEmail));
//        String jwtToken = null;
//        String role = null;
//        AuthenticationResponse authenticationResponse = null;
//        if (userEmail.endsWith(LECTURER_EMAIL_COM)) {
//            var user = lecturerRepository.findAllByEmail(userEmail);
//            if (user.isEmpty()) {
//                return register(token, userEmail);
//            } else {
//                authenticationResponse = AuthenticationResponse.builder()
//                        .token(jwtService.generateToken(user.get()))
//                        .role("LECTURER")
//                        .userInfo(
//                                Lecturer.builder().name(user.get().getName())
//                                        .email(user.get().getEmail())
//                                        .picture(user.get().getPicture())
//                                        .build()
//                        )
//                        .build();
//            }
//        } else {
//            var user = studentRepository.findAllByEmail(userEmail);
//            if (user.isEmpty()) {
//                return register(token, userEmail);
//            } else {
//                authenticationResponse = AuthenticationResponse.builder()
//                        .token(jwtService.generateToken(user.get()))
//                        .role("STUDENT")
//                        .userInfo(
//                                Student.builder().name(user.get().getName())
//                                        .email(user.get().getEmail())
//                                        .picture(user.get().getPicture())
//                                        .build()
//                        )
//                        .build();
//            }
//        }
//        return authenticationResponse;
//    }
//
//    public AuthenticationResponse register(String token, String userEmail) {
//        String jwtToken = null;
//        String role = null;
//        AuthenticationResponse authenticationResponse = null;
//        if (userEmail.endsWith(LECTURER_EMAIL_COM)) {
//            var user = Lecturer.builder()
//                    .id(getUserInfo(token).get("sub").getAsString())
//                    .name(getUserInfo(token).get("name").getAsString())
//                    .email(userEmail)
//                    .picture(getUserInfo(token).get("picture").getAsString())
//                    .build();
//            authenticationResponse = AuthenticationResponse.builder()
//                    .token(jwtService.generateToken(user))
//                    .role("LECTURER")
//                    .userInfo(
//                            Lecturer.builder().name(user.getName())
//                                    .email(user.getEmail())
//                                    .picture(user.getPicture())
//                                    .build()
//                    )
//                    .build();
//            lecturerRepository.save(user);
//        } else {
//            var user = Student.builder()
//                    .id(getUserInfo(token).get("sub").getAsString())
//                    .name(getUserInfo(token).get("name").getAsString())
//                    .email(userEmail)
//                    .picture(getUserInfo(token).get("picture").getAsString())
//                    .build();
//            authenticationResponse = AuthenticationResponse.builder()
//                    .token(jwtService.generateToken(user))
//                    .role("STUDENT")
//                    .userInfo(
//                            Student.builder().name(user.getName())
//                                    .email(user.getEmail())
//                                    .picture(user.getPicture())
//                                    .build()
//                    )
//                    .build();
//            studentRepository.save(user);
//        }
//        return authenticationResponse;
//    }
}
