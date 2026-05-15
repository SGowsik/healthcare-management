package com.binarybot.user_service.dto;

import lombok.Data;

@Data
public class UserResponseDto {

    private String userName;
    private Long userMobileNumber;
    private String userMail;

}
