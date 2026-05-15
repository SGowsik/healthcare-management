package com.binarybot.user_service.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    private String userName;
    private String password;
    private Long userMobileNumber;
    private String userMail;
    private String userAdderess;
    private String userRole;

}
