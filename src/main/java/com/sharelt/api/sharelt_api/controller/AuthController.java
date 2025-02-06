package com.sharelt.api.sharelt_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharelt.api.sharelt_api.exception.AuthenticationException;
import com.sharelt.api.sharelt_api.request.SignInRequest;
import com.sharelt.api.sharelt_api.request.SignUpRequest;
import com.sharelt.api.sharelt_api.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest)
            throws AuthenticationException {
        return authService.authenticateUser(signInRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
            throws AuthenticationException {
        return authService.registerUser(signUpRequest);
    }

}
