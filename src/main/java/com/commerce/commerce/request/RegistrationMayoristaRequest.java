package com.commerce.commerce.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationMayoristaRequest {
    private final String username;
    private final String email;
    private final String password;
    private final String country;
    private final String description;
    private final String name;
    private final String producType;
    private final String sector;
    private final Boolean available;
}
