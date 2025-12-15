package com.nzimbu_yeno.nzimbu.controller;

import com.nzimbu_yeno.nzimbu.domain.dto.AuthResponse;
import com.nzimbu_yeno.nzimbu.domain.dto.LoginRequest;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import com.nzimbu_yeno.nzimbu.repository.UserRepository;
import com.nzimbu_yeno.nzimbu.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        User user =  authenticationService.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        String accessToken = authenticationService.generateToken(user);
        String refreshToken = authenticationService.generateRefreshToken(user);
        AuthResponse authResponse = AuthResponse.builder()
                .userId(user.getId())
                .token(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(6000000L)
                .build();
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestHeader("Authorization") String authHeader){

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        String refreshToken = authHeader.substring(7);

        if (!authenticationService.isRefreshTokenValid(refreshToken)) {
            return ResponseEntity.status(401).build();
        }

        String email = authenticationService.extractEmail(refreshToken);

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newAccessToken = authenticationService.generateToken(user);
        String newRefreshToken = authenticationService.generateRefreshToken(user);

        AuthResponse authResponse = AuthResponse.builder()
                .userId(user.getId())
                .token(newAccessToken)
                .refreshToken(newRefreshToken)
                .expiresIn(600000L)
                .build();

        return ResponseEntity.ok(authResponse);
    }
}
