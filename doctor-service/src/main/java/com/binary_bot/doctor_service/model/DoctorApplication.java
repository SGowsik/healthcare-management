package com.binary_bot.doctor_service.model;

import com.binary_bot.doctor_service.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorApplication {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String specialization;
    private String licenseNumber;

    private String hospitalName;
    private int experienceYears;
    private String qualification;

    @Enumerated(EnumType.STRING)
    private Status status; // PENDING, APPROVED, REJECTED
}

