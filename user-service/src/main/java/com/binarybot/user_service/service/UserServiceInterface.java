package com.binarybot.user_service.service;

import com.binarybot.user_service.dto.LoginRequestDto;
import com.binarybot.user_service.dto.LoginResponseDto;
import com.binarybot.user_service.dto.UserRequestDto;
import com.binarybot.user_service.dto.UserResponseDto;

public interface UserServiceInterface {

    public UserResponseDto createUsers(UserRequestDto userRequestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    public UserResponseDto findUser(Long id);

    public UserResponseDto updateUser(Long userId , UserRequestDto requestDto);

    public  void deleteUser (Long userId );
}
