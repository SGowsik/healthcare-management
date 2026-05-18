package com.binary_bot.doctor_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorApplicationResponseDto {
    private Long id;
    private Long userId;
    private String specialization;
    private String licenseNumber;
    private String status;;
}
