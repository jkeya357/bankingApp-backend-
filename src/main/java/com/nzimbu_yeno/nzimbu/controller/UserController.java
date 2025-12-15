package com.nzimbu_yeno.nzimbu.controller;

import com.nzimbu_yeno.nzimbu.domain.dto.*;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import com.nzimbu_yeno.nzimbu.mapper.UserMapper;
import com.nzimbu_yeno.nzimbu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    ResponseEntity<List<UserDto>> getUser(){
        List<User> user = userService.getUsers();
        List<UserDto> userDtos = userMapper.toDtoList(user);
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping
    ResponseEntity<UserDto> createUser(
            @RequestBody CreateUserRequestDto createUserRequestDto
            ){

        CreateUserRequest createUserRequest = userMapper.toUserRequest(createUserRequestDto);
        User user = userService.createUser(createUserRequest);
        UserDto userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}")
    ResponseEntity<UpdateUserDto> updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequestDto updateUserRequestDto
    ){
        UpdateUserRequest updateUserRequest = userMapper.toUpdateRequest(updateUserRequestDto);
        User user = userService.updateUser(id, updateUserRequest);
        UpdateUserDto dto = userMapper.toUpdateUserDto(user);
        return ResponseEntity.ok(dto);
    }



}
