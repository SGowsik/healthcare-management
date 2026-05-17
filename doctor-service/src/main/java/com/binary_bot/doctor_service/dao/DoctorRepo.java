package com.binary_bot.doctor_service.dao;

import com.binary_bot.doctor_service.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {

    boolean existsByUserId(Long userId);

    Optional<Doctor> findByUserId(Long userId);
}
