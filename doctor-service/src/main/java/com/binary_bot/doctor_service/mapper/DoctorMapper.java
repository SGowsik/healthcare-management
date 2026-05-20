package com.binary_bot.doctor_service.mapper;

import com.binary_bot.doctor_service.dto.DoctorApplyRequestDto;
import com.binary_bot.doctor_service.dto.DoctorResponseDto;
import com.binary_bot.doctor_service.dto.UserDto;
import com.binary_bot.doctor_service.model.Doctor;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {



        public static Doctor toEntity(DoctorApplyRequestDto dto) {
            Doctor doctor = new Doctor();
            doctor.setUserId(doctor.getUserId());
            doctor.setSpecialization(dto.getSpecialization());

//            doctor.setQualification(dto.getQualification());
//            doctor.setExperienceYears(dto.getExperienceYears());
//            doctor.setHospitalName(dto.getHospitalName());

            // important: default value
            doctor.setVerified(false);

            return doctor;
        }

    public DoctorResponseDto toDTO(Doctor doc, UserDto user) {

        DoctorResponseDto dto = new DoctorResponseDto();

        dto.setId(doc.getId());
        dto.setUserId(doc.getUserId());

        dto.setSpecialization(doc.getSpecialization());
        dto.setQualification(doc.getQualification());
        dto.setExperienceYears(doc.getExperienceYears());
        dto.setHospitalName(doc.getHospitalName());

        dto.setVerified(doc.isVerified());

        //  from User Service
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setMobileNumber(user.getMobileNumber());

        return dto;
    }

    }

