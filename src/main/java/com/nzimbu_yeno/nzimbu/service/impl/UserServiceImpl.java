package com.nzimbu_yeno.nzimbu.service.impl;

import com.nzimbu_yeno.nzimbu.domain.dto.Changes;
import com.nzimbu_yeno.nzimbu.domain.dto.CreateUserRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.UpdateUserRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.UpdateUserResponseDto;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import com.nzimbu_yeno.nzimbu.repository.UserRepository;
import com.nzimbu_yeno.nzimbu.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    Changes userUpdates = new Changes();

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {

        Optional<User> duplicateUser = userRepository.findUserByEmailOrUserName(createUserRequest.getEmail(), createUserRequest.getUserName());

        if(duplicateUser.isPresent()) throw new IllegalArgumentException("This user already exists");

        User user = new User();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setUserName(createUserRequest.getUserName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setProfilePicture(createUserRequest.getProfilePicture());

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Map<String, UpdateUserResponseDto> changes = new HashMap<>();

        if(updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().equals(user.getEmail())){
            userRepository.findUserByEmail(updateUserRequest.getEmail()).ifPresent(existing -> {
                if(!existing.getId().equals(user.getId())){
                    throw new IllegalArgumentException("This user already exists");
                }
            });
        }

        if (updateUserRequest.getUsername() != null && !updateUserRequest.getUsername().equals(user.getUserName())) {
            userUpdates.add("email", user.getEmail(), updateUserRequest.getEmail());
            user.setUserName(updateUserRequest.getUsername());
        }

        if(updateUserRequest.getFirstName() != null){
            userUpdates.add("firstName", user.getFirstName(), updateUserRequest.getFirstName());
            user.setFirstName(updateUserRequest.getFirstName());
        }

        if(updateUserRequest.getLastName() != null){
            userUpdates.add("lastName", user.getLastName(), user.getLastName());
            user.setLastName(updateUserRequest.getLastName());
        }

        if(updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().equals(user.getEmail())){
            userUpdates.add("email", user.getEmail(), updateUserRequest.getEmail());
            user.setEmail(updateUserRequest.getEmail());
        }

        if(updateUserRequest.getPassword() != null){
            user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }

        if(updateUserRequest.getProfilePicture() != null){
            userUpdates.add("profilePicture", user.getProfilePicture(), updateUserRequest.getProfilePicture());
            user.setProfilePicture(updateUserRequest.getProfilePicture());
        }
        return userRepository.save(user);
    }
}
