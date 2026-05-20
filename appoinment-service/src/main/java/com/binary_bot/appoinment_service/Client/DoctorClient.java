package com.binary_bot.appoinment_service.Client;

import com.binary_bot.appoinment_service.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "doctor-service")
public interface DoctorClient {

    @GetMapping("/doctor/{id}")
    DoctorDto getDoctorById(@PathVariable Long id);
}
