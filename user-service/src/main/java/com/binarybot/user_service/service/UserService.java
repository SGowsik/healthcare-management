package com.binarybot.user_service.service;

import com.binarybot.user_service.dao.UserRepo;
import com.binarybot.user_service.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepo userRepo;

    public Users createUsers(Users user){

      return userRepo.save(user);
    }


}
