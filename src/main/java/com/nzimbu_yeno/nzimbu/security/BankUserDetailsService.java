package com.nzimbu_yeno.nzimbu.security;

import com.nzimbu_yeno.nzimbu.domain.entity.User;
import com.nzimbu_yeno.nzimbu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class BankUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow (() -> new UsernameNotFoundException("User not found"));
        return new BankUserDetails(user);
    }
}
