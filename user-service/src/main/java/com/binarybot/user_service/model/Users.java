package com.binarybot.user_service.model;

import com.binarybot.user_service.enums.Role;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobileNumber;

    private String address;


    @Enumerated(EnumType.STRING)
    private Role role;

}
