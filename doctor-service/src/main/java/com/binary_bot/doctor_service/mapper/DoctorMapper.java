package com.binary_bot.doctor_service.mapper;

import com.binary_bot.doctor_service.dto.DoctorRequestDto;
import com.binary_bot.doctor_service.dto.DoctorResponseDto;
import com.binary_bot.doctor_service.model.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorResponseDto toDTO(Doctor doctor);

    Doctor toEntity(DoctorRequestDto dto);
}
