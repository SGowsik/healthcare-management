package com.binary_bot.doctor_service.dto;

import lombok.Data;

@Data
public class DoctorResponseDto {


    private Long id;
    private Long userId;

    private String specialization;
    private String qualification;
    private int experienceYears;
    private String hospitalName;

    private boolean verified;
}
