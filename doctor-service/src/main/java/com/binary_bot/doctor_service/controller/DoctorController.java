package com.binary_bot.doctor_service.controller;

import com.binary_bot.doctor_service.dto.DoctorApplicationResponseDto;
import com.binary_bot.doctor_service.dto.DoctorApplyRequestDto;
import com.binary_bot.doctor_service.service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;

    @PostMapping("/apply")
    public ResponseEntity<DoctorApplicationResponseDto> applyDoctor(
            @RequestParam Long userId,
            @Valid @RequestBody DoctorApplyRequestDto request) {

        return ResponseEntity.ok(doctorService.applyDoctor(userId, request));
    }

    @PutMapping("/admin/approve/{id}")
    public ResponseEntity<?> approveDoctor(@PathVariable Long id) {

        return ResponseEntity.ok(doctorService.approveDoctor(id));
    }

    @PutMapping("/admin/reject/{id}")
    public ResponseEntity<?> rejectDoctor(@PathVariable Long id) {

        doctorService.rejectDoctor(id);
        return ResponseEntity.ok("Rejected successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getDoctorByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(doctorService.getDoctorByUserId(userId));
    }

    @GetMapping("/applications")
    public ResponseEntity<?> getAllApplications() {
        return ResponseEntity.ok(doctorService.getAllApplications());
    }

    @GetMapping("/application/{userId}")
    public ResponseEntity<?> getApplicationByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(doctorService.getApplicationByUserId(userId));
    }
}