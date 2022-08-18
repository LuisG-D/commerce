package com.commerce.commerce.request;


import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
}
