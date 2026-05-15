package com.binarybot.user_service.controller;

import com.binarybot.user_service.dto.UserRequestDto;
import com.binarybot.user_service.dto.UserResponseDto;
import com.binarybot.user_service.model.Users;
import com.binarybot.user_service.service.UserService;
import com.binarybot.user_service.service.UserServiceInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {


     UserServiceInterface userServiceinterface ;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(
            @Valid @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto response = userServiceinterface.createUsers(userRequestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
