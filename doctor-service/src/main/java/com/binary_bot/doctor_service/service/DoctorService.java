package com.binary_bot.doctor_service.service;

import com.binary_bot.doctor_service.dao.DoctorApplicationRepo;
import com.binary_bot.doctor_service.dao.DoctorRepo;
import com.binary_bot.doctor_service.dto.DoctorApplyRequestDto;
import com.binary_bot.doctor_service.enums.Status;
import com.binary_bot.doctor_service.model.Doctor;
import com.binary_bot.doctor_service.model.DoctorApplication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService implements DoctorServiceInterface {

    private final DoctorRepo doctorRepo;
    private DoctorApplicationRepo doctorApplicationRepo;

    public DoctorApplication applyDoctor(Long userId, DoctorApplyRequestDto request) {

        if (doctorRepo.existsByUserId(userId)) {
            throw new RuntimeException("User is already a doctor");
        }

        //  Validation 2: Already applied
        if (doctorApplicationRepo.existsByUserIdAndStatus(userId, Status.PENDING)) {
            throw new RuntimeException("Application already pending");
        }

        //  Validation 3: Basic input validation
        if (request.getLicenseNumber() == null || request.getLicenseNumber().isEmpty()) {
            throw new RuntimeException("License number is required");
        }

        DoctorApplication app = new DoctorApplication();
        app.setUserId(userId);
        app.setSpecialization(request.getSpecialization());
        app.setLicenseNumber(request.getLicenseNumber());
        app.setStatus(Status.PENDING);

        return doctorApplicationRepo.save(app);
    }

    public Doctor approveDoctor(Long applicationId) {

        DoctorApplication app = doctorApplicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        //Already processed
        if (app.getStatus() != Status.PENDING) {
            throw new RuntimeException("Application already processed");
        }

        //  Prevent duplicate doctor
        if (doctorRepo.existsByUserId(app.getUserId())) {
            throw new RuntimeException("Doctor already exists");
        }

        // Update application
        app.setStatus(Status.APPROVED);
        doctorApplicationRepo.save(app);

        // Create doctor profile
        Doctor doctor = new Doctor();
        doctor.setUserId(app.getUserId());
        doctor.setSpecialization(app.getSpecialization());
        doctor.setVerified(true);

        return doctorRepo.save(doctor);
    }

    public void rejectDoctor(Long applicationId) {

        DoctorApplication app = doctorApplicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (app.getStatus() != Status.PENDING) {
            throw new RuntimeException("Application already processed");
        }

        app.setStatus(Status.REJECTED);
        doctorApplicationRepo.save(app);
    }

    public Doctor getDoctorByUserId(Long userId) {

        return doctorRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}
