package com.binarybot.user_service.mapper;

import com.binarybot.user_service.dto.UserRequestDto;
import com.binarybot.user_service.dto.UserResponseDto;
import com.binarybot.user_service.model.Users;

public class DtoMapper {

public static Users toEntity(UserRequestDto userRequestDto){

    /*Users users = new Users();

    users.setName(userRequestDto.getName());
    users.setPassword(userRequestDto.getPassword());
    users.setEmail(userRequestDto.getEmail());
    users.setRole(userRequestDto.getRole());
    users.setMobileNumber(userRequestDto.getMobileNumber());
    users.setAddress(userRequestDto.getAddress());*/

    return Users.builder()
            .name(userRequestDto.getName())
            .password(userRequestDto.getPassword())
            .mobileNumber(userRequestDto.getMobileNumber())
            .email(userRequestDto.getEmail())
            .address(userRequestDto.getAddress())
            .build();

}

public static UserResponseDto toDto(Users users){

    /*UserResponseDto userResponseDto = new UserResponseDto();

    userResponseDto.setName(users.getName());
    userResponseDto.setEmail(users.getEmail());
    userResponseDto.setMobileNumber(users.getMobileNumber());

    return userResponseDto;*/
    return UserResponseDto.builder()

            .name(users.getName())
            .email(users.getEmail())
            .mobileNumber(users.getMobileNumber())
            .role(users.getRole().name())
            .build();
}
}
