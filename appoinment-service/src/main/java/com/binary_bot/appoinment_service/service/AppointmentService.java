package com.binary_bot.appoinment_service.service;

import com.binary_bot.appoinment_service.Client.DoctorClient;
import com.binary_bot.appoinment_service.dao.AppointmentRepo;
import com.binary_bot.appoinment_service.dto.AppointmentRequestDto;
import com.binary_bot.appoinment_service.dto.AppointmentResponseDto;
import com.binary_bot.appoinment_service.dto.DoctorDto;
import com.binary_bot.appoinment_service.dtomapper.AppointmentMapper;
import com.binary_bot.appoinment_service.enums.Status;
import com.binary_bot.appoinment_service.model.Appointment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.binary_bot.appoinment_service.dto.DoctorDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public  class AppointmentService {

    private final AppointmentRepo repo;
    private final DoctorClient doctorClient;
    private final AppointmentMapper mapper;

    //  BOOK APPOINTMENT
    public AppointmentResponseDto book(AppointmentRequestDto request) {

        //  Step 1: Validate doctor
        DoctorDto doctor;

        try {
            doctor = doctorClient.getDoctorById(request.getDoctorId());
        } catch (Exception e) {
            throw new RuntimeException("Doctor not found");
        }

        //  Optional validation
        // if (!doctor.isVerified()) {
        //     throw new RuntimeException("Doctor is not approved");
        // }

        //  Step 2: Create entity
        Appointment appt = mapper.toEntity(
                request.getPatientId(),
                request.getDoctorId(),
                request.getAppointmentTime()
        );

        //  Step 3: Save
        Appointment saved = repo.save(appt);

        //  Step 4: Return with doctor details
        return mapper.toDto(saved, doctor);
    }

    //  GET PATIENT APPOINTMENTS
    public List<AppointmentResponseDto> getByPatientId(Long patientId) {

        List<Appointment> list = repo.findByPatientId(patientId);

        return list.stream().map(app -> {

            DoctorDto doctor = doctorClient.getDoctorById(app.getDoctorId());

            return mapper.toDto(app, doctor);

        }).toList();
    }

    //  GET DOCTOR APPOINTMENTS
    public List<AppointmentResponseDto> getByDoctor(Long doctorId) {

        return repo.findByDoctorId(doctorId)
                .stream()
                .map(app -> {

                    DoctorDto doctor = doctorClient.getDoctorById(app.getDoctorId());

                    return mapper.toDto(app, doctor);

                })
                .toList();
    }

    //  CANCEL APPOINTMENT
    public String cancel(Long appointmentId) {

        Appointment appt = repo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appt.setStatus(Status.CANCELLED);

        repo.save(appt);

        return "Appointment cancelled successfully";
    }

}