package com.binary_bot.doctor_service.dto;//package com.binary_bot.doctor_service.dto;
//
//import lombok.Data;
//
//@Data
//public class DoctorRequestDto {
//    private Long userId;
//    private String specialization;
//    private String qualification;
//    private int experienceYears;
//    private String hospitalName;
//}

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String mobileNumber;
}