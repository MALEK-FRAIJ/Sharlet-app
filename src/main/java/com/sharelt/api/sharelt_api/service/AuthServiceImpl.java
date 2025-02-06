package com.sharelt.api.sharelt_api.service;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sharelt.api.sharelt_api.entity.user.User;
import com.sharelt.api.sharelt_api.exception.AuthenticationException;
import com.sharelt.api.sharelt_api.repository.UserRepository;
import com.sharelt.api.sharelt_api.request.SignInRequest;
import com.sharelt.api.sharelt_api.request.SignUpRequest;
import com.sharelt.api.sharelt_api.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) throws AuthenticationException {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AuthenticationException("Username is already taken..");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AuthenticationException("Email is already in use..");
        }

        User savedUser = userService.createUser(signUpRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/{username}")
                .buildAndExpand(savedUser.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully."));
    }

    @Override
    public ResponseEntity<?> authenticateUser(SignInRequest signInRequest) throws AuthenticationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticateUser'");
    }

}
