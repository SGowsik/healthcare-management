package com.binarybot.user_service.service;

import com.binarybot.user_service.dto.UserRequestDto;
import com.binarybot.user_service.dto.UserResponseDto;
import com.binarybot.user_service.model.Users;

public interface UserServiceInterface {

    public UserResponseDto createUsers(UserRequestDto userRequestDto);
}
