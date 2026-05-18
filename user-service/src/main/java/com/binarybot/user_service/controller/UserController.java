package com.binarybot.user_service.controller;

import com.binarybot.user_service.dto.LoginRequestDto;
import com.binarybot.user_service.dto.LoginResponseDto;
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
//@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {

        LoginResponseDto response = userServiceinterface.login(request);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{userId}")

    public  ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId){

        return ResponseEntity.ok(userServiceinterface.findUser(userId));
    }

    @PutMapping("/{userId}")

    public ResponseEntity <UserResponseDto> updateUser(
            @PathVariable Long userId ,@RequestBody UserRequestDto requestDto){
        return ResponseEntity.ok(userServiceinterface.updateUser(userId,requestDto));
    }

    @DeleteMapping("/userId")

    public ResponseEntity<String> deleteUser(@PathVariable Long userId){

        userServiceinterface.deleteUser(userId);

        return ResponseEntity.ok("deleted successfully ");
    }

}
