package com.commerce.commerce.service;

import com.commerce.commerce.entity.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if(confirmationToken.getConfirmedAt() == null){
            throw new IllegalStateException("email not confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        return "SingIn";

    }


}
