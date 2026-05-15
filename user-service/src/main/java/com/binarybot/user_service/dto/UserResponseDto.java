package com.binarybot.user_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private String name;
    private String mobileNumber;
    private String email;
    private String role;

}
