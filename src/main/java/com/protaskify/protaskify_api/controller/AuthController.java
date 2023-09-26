package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.auth.AuthenticationRequest;
import com.protaskify.protaskify_api.model.auth.AuthenticationResponse;
import com.protaskify.protaskify_api.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
