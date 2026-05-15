package com.binarybot.user_service.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    PATIENT,
    DOCTOR,
    ADMIN;

    @JsonCreator
    public static Role from(String value) {
        return Role.valueOf(value.toUpperCase());
    }
}
