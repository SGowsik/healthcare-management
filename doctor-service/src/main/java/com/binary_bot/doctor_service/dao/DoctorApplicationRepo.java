package com.binary_bot.doctor_service.dao;

import com.binary_bot.doctor_service.enums.Status;
import com.binary_bot.doctor_service.model.DoctorApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DoctorApplicationRepo extends JpaRepository<DoctorApplication,Long> {

    boolean existsByUserIdAndStatus(Long userId, Status status);

    Optional<DoctorApplication> findByUserId(Long userId);
}
