package com.commerce.commerce.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LoginRequest {
    @NotBlank

    private String email;
    @NotBlank

    private String password;
}

