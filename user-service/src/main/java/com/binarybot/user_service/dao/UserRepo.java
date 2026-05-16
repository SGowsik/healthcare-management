package com.binarybot.user_service.dao;

import com.binarybot.user_service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    boolean existsByEmail(String email);

  Optional<Users> findByEmail(String email);



}
