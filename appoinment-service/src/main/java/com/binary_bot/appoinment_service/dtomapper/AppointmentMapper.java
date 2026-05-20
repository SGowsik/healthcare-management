package com.binary_bot.appoinment_service.dtomapper;


import com.binary_bot.appoinment_service.dto.AppointmentResponseDto;
import com.binary_bot.appoinment_service.dto.DoctorDto;
import com.binary_bot.appoinment_service.enums.Status;
import com.binary_bot.appoinment_service.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {


    //  DTO → Entity
    public Appointment toEntity(Long patientId, Long doctorId,
                                java.time.LocalDateTime time) {

        Appointment appointment = new Appointment();
        appointment.setPatientId(patientId);
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentTime(time);
        appointment.setStatus(Status.BOOKED);

        return appointment;
    }

    //  Entity + Doctor → DTO
    public AppointmentResponseDto toDto(Appointment app, DoctorDto doctor) {

        AppointmentResponseDto dto = new AppointmentResponseDto();

        dto.setId(app.getId());
        dto.setDoctorId(app.getDoctorId());
        dto.setPatientId(app.getPatientId());

        //  Doctor details from Feign
        dto.setDoctorName(doctor.getName());
        System.out.println(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());

        dto.setAppointmentTime(app.getAppointmentTime());
        dto.setStatus(app.getStatus().name());

        return dto;
    }
}