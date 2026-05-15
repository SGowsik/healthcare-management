package com.binarybot.user_service.service;

import com.binarybot.user_service.dao.UserRepo;
import com.binarybot.user_service.dto.UserRequestDto;
import com.binarybot.user_service.dto.UserResponseDto;
import com.binarybot.user_service.mapper.DtoMapper;
import com.binarybot.user_service.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

     private  final UserRepo userRepo;

    @Override
    public UserResponseDto createUsers(UserRequestDto userRequestDto) {

        if (userRepo.existsByEmail(userRequestDto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Users users = DtoMapper.toEntity(userRequestDto);

        return DtoMapper.toDto(userRepo.save(users));
    }

   /* public UserResponseDto createUsers(UserRequestDto userRequestDto){

      Users users = DtoMapper.toEntity(userRequestDto);

      Users saved = userRepo.save(users);

      return DtoMapper.toDto(saved);
    }*/


}
