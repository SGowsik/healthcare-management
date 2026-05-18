package com.binary_bot.doctor_service.service;

import com.binary_bot.doctor_service.dao.DoctorApplicationRepo;
import com.binary_bot.doctor_service.dao.DoctorRepo;
import com.binary_bot.doctor_service.dto.DoctorApplicationResponseDto;
import com.binary_bot.doctor_service.dto.DoctorApplyRequestDto;
import com.binary_bot.doctor_service.dto.DoctorResponseDto;
import com.binary_bot.doctor_service.enums.Status;
import com.binary_bot.doctor_service.mapper.DoctorMapper;
import com.binary_bot.doctor_service.model.Doctor;
import com.binary_bot.doctor_service.model.DoctorApplication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService implements DoctorServiceInterface {

    private final DoctorRepo doctorRepo;
    private final DoctorApplicationRepo doctorApplicationRepo;
    private final DoctorMapper doctorMapper;

    //  Apply Doctor
    public DoctorApplicationResponseDto applyDoctor(Long userId, DoctorApplyRequestDto request) {

        //  Validation
        if (doctorRepo.existsByUserId(userId)) {
            throw new RuntimeException("User is already a doctor");
        }

        if (doctorApplicationRepo.existsByUserIdAndStatus(userId, Status.PENDING)) {
            throw new RuntimeException("Application already pending");
        }

        // (Bean Validation should handle this, but keeping safe check is okay)
        if (request.getLicenseNumber() == null || request.getLicenseNumber().isEmpty()) {
            throw new RuntimeException("License number is required");
        }

        DoctorApplication app = new DoctorApplication();
        app.setUserId(userId);
        app.setSpecialization(request.getSpecialization());
        app.setLicenseNumber(request.getLicenseNumber());
        app.setStatus(Status.PENDING);

        DoctorApplication saved = doctorApplicationRepo.save(app);

        return new DoctorApplicationResponseDto(
                saved.getId(),
                saved.getUserId(),
                saved.getSpecialization(),
                saved.getLicenseNumber(),
                saved.getStatus().name()
        );
    }

    //  Approve Doctor
    public DoctorResponseDto approveDoctor(Long applicationId) {

        DoctorApplication app = doctorApplicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (app.getStatus() != Status.PENDING) {
            throw new RuntimeException("Application already processed");
        }

        if (doctorRepo.existsByUserId(app.getUserId())) {
            throw new RuntimeException("Doctor already exists");
        }

        // Update application
        app.setStatus(Status.APPROVED);
        doctorApplicationRepo.save(app);

        // Create doctor
        Doctor doctor = new Doctor();
        doctor.setUserId(app.getUserId());
        doctor.setSpecialization(app.getSpecialization());
        doctor.setVerified(true);

        Doctor saved = doctorRepo.save(doctor);

        return doctorMapper.toDTO(saved);
    }

    //  Reject Doctor
    public void rejectDoctor(Long applicationId) {

        DoctorApplication app = doctorApplicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (app.getStatus() != Status.PENDING) {
            throw new RuntimeException("Application already processed");
        }

        app.setStatus(Status.REJECTED);
        doctorApplicationRepo.save(app);
    }

    //  Get Doctor
    public DoctorResponseDto getDoctorByUserId(Long userId) {

        Doctor doctor = doctorRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return doctorMapper.toDTO(doctor);
    }

    //   Admin Dashboard
    public List<DoctorApplicationResponseDto> getAllApplications() {

        return doctorApplicationRepo.findAll()
                .stream()
                .map(app -> new DoctorApplicationResponseDto(
                        app.getId(),
                        app.getUserId(),
                        app.getSpecialization(),
                        app.getLicenseNumber(),
                        app.getStatus().name()
                ))
                .toList();
    }

    public DoctorApplicationResponseDto getApplicationByUserId(Long userId) {

        DoctorApplication app = doctorApplicationRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return new DoctorApplicationResponseDto(
                app.getId(),
                app.getUserId(),
                app.getSpecialization(),
                app.getLicenseNumber(),
                app.getStatus().name()
        );
    }
}