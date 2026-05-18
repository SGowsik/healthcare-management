package com.binary_bot.doctor_service.mapper;

import com.binary_bot.doctor_service.dto.DoctorApplyRequestDto;
import com.binary_bot.doctor_service.dto.DoctorResponseDto;
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

        public  static DoctorResponseDto toDTO(Doctor doctor) {
            DoctorResponseDto dto = new DoctorResponseDto();
            dto.setId(doctor.getId());
            dto.setSpecialization(doctor.getSpecialization());
            dto.setQualification(doctor.getQualification());
            dto.setExperienceYears(doctor.getExperienceYears());
            dto.setHospitalName(doctor.getHospitalName());
            dto.setVerified(doctor.isVerified());

            return dto;
        }
    }

