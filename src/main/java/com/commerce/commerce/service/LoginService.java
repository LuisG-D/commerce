package com.commerce.commerce.service;

import com.commerce.commerce.entity.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoginService {
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;

    @Transactional
    public String singinUser(String token){
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));
        if(confirmationToken.getConfirmedAt() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"email not confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        throw new ResponseStatusException(HttpStatus.OK, "Datos de usuario correctos");

    }


}