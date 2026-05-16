package com.binarybot.user_service.service;

import com.binarybot.user_service.dao.UserRepo;
import com.binarybot.user_service.dto.LoginRequestDto;
import com.binarybot.user_service.dto.UserRequestDto;
import com.binarybot.user_service.dto.UserResponseDto;
import com.binarybot.user_service.enums.Role;
import com.binarybot.user_service.mapper.DtoMapper;
import com.binarybot.user_service.model.Users;
import lombok.AllArgsConstructor;
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

        users.setRole(Role.PATIENT);

        return DtoMapper.toDto(userRepo.save(users));
    }

    @Override
    public void login(LoginRequestDto loginRequestDto) {

       Users users= userRepo.findByEmail(loginRequestDto.getEmail())
               .orElseThrow(()->new RuntimeException(""));
    }
    
    public UserResponseDto findUser(Long id){
      Users users= userRepo.findById(id)
              .orElseThrow(()->new RuntimeException("user not found"));

      return DtoMapper.toDto(users);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {

        Users users= userRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("user not found"));

        users.setName(requestDto.getName());
        users.setMobileNumber(requestDto.getMobileNumber());
        users.setEmail(requestDto.getEmail());
        users.setAddress(requestDto.getAddress());

        Users upadated = userRepo.save(users);

        return DtoMapper.toDto(upadated);
    }

    @Override
    public void  deleteUser(Long userId) {
         userRepo.deleteById(userId);
    }

   /* public UserResponseDto createUsers(UserRequestDto userRequestDto){

      Users users = DtoMapper.toEntity(userRequestDto);

      Users saved = userRepo.save(users);

      return DtoMapper.toDto(saved);
    }*/


}
