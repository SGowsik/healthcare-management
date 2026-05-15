package com.binarybot.user_service.controller;

import com.binarybot.user_service.model.Users;
import com.binarybot.user_service.service.UserService;
import com.binarybot.user_service.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInterface userServiceinterface ;

   @PostMapping("/create")

    public Users createUsers(@RequestBody Users user){

     return  userServiceinterface.createUsers(user);

   }

}
