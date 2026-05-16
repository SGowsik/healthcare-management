package com.binarybot.user_service.service;

import com.binarybot.user_service.dto.LoginRequestDto;
import com.binarybot.user_service.dto.UserRequestDto;
import com.binarybot.user_service.dto.UserResponseDto;
import com.binarybot.user_service.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserServiceInterface {

    public UserResponseDto createUsers(UserRequestDto userRequestDto);

    void login(LoginRequestDto loginRequestDto);

    public UserResponseDto findUser(Long id);

    public UserResponseDto updateUser(Long userId , UserRequestDto requestDto);

    public  void deleteUser (Long userId );
}
