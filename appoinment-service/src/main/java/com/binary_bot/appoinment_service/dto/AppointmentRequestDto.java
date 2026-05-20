package com.binary_bot.appoinment_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentRequestDto {

    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
}