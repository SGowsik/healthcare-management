package com.binary_bot.appoinment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String specialization;
    private LocalDateTime appointmentTime;
    private String status;
}