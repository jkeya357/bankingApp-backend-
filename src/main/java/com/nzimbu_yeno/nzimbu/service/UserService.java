package com.nzimbu_yeno.nzimbu.service;

import com.nzimbu_yeno.nzimbu.domain.dto.CreateUserRequest;
import com.nzimbu_yeno.nzimbu.domain.dto.UpdateUserRequest;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    List<User> getUsers();
    User createUser(CreateUserRequest createUserRequest);
    User updateUser(UUID id,UpdateUserRequest updateUserRequest);
}
