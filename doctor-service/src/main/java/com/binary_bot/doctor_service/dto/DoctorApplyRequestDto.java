package com.binary_bot.doctor_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorApplyRequestDto {

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @NotBlank(message = "Hospital name is required")
    private String hospitalName;

    private int experienceYears;

    private String qualification;
}
