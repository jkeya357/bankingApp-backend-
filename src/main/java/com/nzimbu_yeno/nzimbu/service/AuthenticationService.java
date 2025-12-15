package com.nzimbu_yeno.nzimbu.service;

import com.nzimbu_yeno.nzimbu.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    User authenticate(String email, String password);
    String generateToken(User user);
    boolean validateToken(String token, UserDetails userDetails);
    boolean isTokenValid(String token);
    String extractEmail(String token);
    String generateRefreshToken(User user);
    boolean isRefreshTokenValid(String token);
}