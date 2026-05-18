package com.binarybot.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginRequestDto {

   @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

   @NotBlank(message = "Password is required")
    private String password;
}
