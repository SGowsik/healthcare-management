package com.binarybot.user_service.controller;

import com.binarybot.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping
    public String getAllUsers(){
        return "Showing all users";
    }

    @GetMapping
    public  String findUser(){
        return userService.findUser();
    }

}
