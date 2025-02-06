package com.sharelt.api.sharelt_api.service;

import org.springframework.http.ResponseEntity;

import com.sharelt.api.sharelt_api.exception.AuthenticationException;
import com.sharelt.api.sharelt_api.request.SignInRequest;
import com.sharelt.api.sharelt_api.request.SignUpRequest;

public interface AuthService {

    ResponseEntity<?> registerUser(SignUpRequest signUpRequest) throws AuthenticationException;

    ResponseEntity<?> authenticateUser(SignInRequest signInRequest) throws AuthenticationException;

}