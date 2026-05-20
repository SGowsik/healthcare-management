package com.binary_bot.appoinment_service.dao;

import com.binary_bot.appoinment_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<com.binary_bot.appoinment_service.model.Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);
}