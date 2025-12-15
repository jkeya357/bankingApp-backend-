package com.nzimbu_yeno.nzimbu.mapper;

import com.nzimbu_yeno.nzimbu.domain.dto.*;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {AccountMapper.class})
public interface UserMapper {

    CreateUserRequest toUserRequest(CreateUserRequestDto createUserRequestDto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> user);

    UpdateUserDto toUpdateUserDto(User user);

    UpdateUserRequest toUpdateRequest(UpdateUserRequestDto updateUserRequestDto);
}
