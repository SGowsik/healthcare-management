package com.binary_bot.appoinment_service.model;

import com.binary_bot.appoinment_service.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;   // from User Service
    private Long doctorId;    // from Doctor Service

    private LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private Status status;
}