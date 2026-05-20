package com.binary_bot.doctor_service.service;
import com.binary_bot.doctor_service.clients.UserClient;
import com.binary_bot.doctor_service.enums.Status;
import com.binary_bot.doctor_service.dao.DoctorApplicationRepo;
import com.binary_bot.doctor_service.dao.DoctorRepo;
import com.binary_bot.doctor_service.dto.DoctorApplicationResponseDto;
import com.binary_bot.doctor_service.dto.DoctorApplyRequestDto;
import com.binary_bot.doctor_service.dto.DoctorResponseDto;

import com.binary_bot.doctor_service.dto.UserDto;
import com.binary_bot.doctor_service.mapper.DoctorMapper;
import com.binary_bot.doctor_service.model.Doctor;
import com.binary_bot.doctor_service.model.DoctorApplication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorService implements DoctorServiceInterface {
    private final DoctorRepo doctorRepo;
    private final DoctorApplicationRepo doctorApplicationRepo;
    private final DoctorMapper doctorMapper;
    private final UserClient userClient;

    // APPLY DOCTOR
    public DoctorApplicationResponseDto applyDoctor(Long userId, DoctorApplyRequestDto request) {

        if (doctorRepo.existsByUserId(userId)) {
            throw new RuntimeException("User is already a doctor");
        }

        if (doctorApplicationRepo.existsByUserIdAndStatus(userId, Status.PENDING)) {
            throw new RuntimeException("Application already pending");
        }

        DoctorApplication app = new DoctorApplication();
        app.setUserId(userId);
        app.setSpecialization(request.getSpecialization());
        app.setLicenseNumber(request.getLicenseNumber());
        app.setHospitalName(request.getHospitalName());
        app.setExperienceYears(request.getExperienceYears());
        app.setQualification(request.getQualification());

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

    // APPROVE DOCTOR
    public DoctorResponseDto approveDoctor(Long applicationId) {

        DoctorApplication app = doctorApplicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (app.getStatus() != Status.PENDING) {
            throw new RuntimeException("Application already processed");
        }

        if (doctorRepo.existsByUserId(app.getUserId())) {
            throw new RuntimeException("Doctor already exists");
        }

        // update status
        app.setStatus(Status.APPROVED);
        doctorApplicationRepo.save(app);

        // create doctor
        Doctor doctor = new Doctor();
        doctor.setUserId(app.getUserId());
        doctor.setSpecialization(app.getSpecialization());
        doctor.setHospitalName(app.getHospitalName());
        doctor.setExperienceYears(app.getExperienceYears());
        doctor.setQualification(app.getQualification());
        doctor.setVerified(true);

        Doctor saved = doctorRepo.save(doctor);

        //  fetch user details
        UserDto user = userClient.getUserById(saved.getUserId());

        return doctorMapper.toDTO(saved, user);
    }

    public void rejectDoctor(Long applicationId) {

        DoctorApplication app = doctorApplicationRepo.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // already processed check
        if (app.getStatus() != Status.PENDING) {
            throw new RuntimeException("Application already processed");
        }

        // update status
        app.setStatus(Status.REJECTED);
        doctorApplicationRepo.save(app);
    }


    public DoctorResponseDto getDoctorById(Long id) {

        Doctor doc = doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        //fetch user data
        UserDto user = userClient.getUserById(doc.getUserId());

        return doctorMapper.toDTO(doc, user);
    }

    // VERIFIED DOCTORS (used in booking )
    public List<DoctorResponseDto> getVerifiedDoctors() {

        return doctorRepo.findByVerifiedTrue()
                .stream()
                .map(doc -> {

                    UserDto user = userClient.getUserById(doc.getUserId());

                    return doctorMapper.toDTO(doc, user);

                })
                .collect(Collectors.toList());
    }



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