package com.binary_bot.doctor_service.controller;

import com.binary_bot.doctor_service.dto.DoctorApplyRequestDto;
import com.binary_bot.doctor_service.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {


    private DoctorService doctorService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyDoctor(@RequestParam Long userId,
                                         @RequestBody DoctorApplyRequestDto request) {

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
}
