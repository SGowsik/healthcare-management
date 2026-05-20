package com.binary_bot.appoinment_service.controller;

import com.binary_bot.appoinment_service.dto.AppointmentResponseDto;
import com.binary_bot.appoinment_service.service.AppointmentService;
import com.binary_bot.appoinment_service.dto.AppointmentRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService service;

    //  Book
    @PostMapping
    public AppointmentResponseDto book(@RequestBody AppointmentRequestDto request) {
        return service.book(request);
    }

    //  Patient
    @GetMapping("/patient/{patientId}")
    public List<AppointmentResponseDto> getPatient(@PathVariable Long patientId) {
        return service.getByPatientId(patientId);
    }

    //  Doctor
    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentResponseDto> getDoctor(@PathVariable Long doctorId) {
        return service.getByDoctor(doctorId);
    }
}