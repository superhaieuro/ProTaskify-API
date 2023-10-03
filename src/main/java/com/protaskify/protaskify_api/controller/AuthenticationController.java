package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.response.AuthenticationResponse;
import com.protaskify.protaskify_api.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody String token) {
        return ResponseEntity.ok(service.authenticate(token));
    }
}
