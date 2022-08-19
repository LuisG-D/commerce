package com.commerce.commerce.service;

import com.commerce.commerce.entity.AppUser;
import com.commerce.commerce.entity.ConfirmationToken;
import com.commerce.commerce.entity.Mayorista;
import com.commerce.commerce.repository.AppUserRepository;
import com.commerce.commerce.repository.MayoristaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
@Validated
public class MayoristaUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final MayoristaRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;



    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException
                        (String.format(USER_NOT_FOUND_MSG, email)));
    }


    public String singUpUser(AppUser appUser, Mayorista mayorista){
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(mayorista.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(mayorista);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                mayorista
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        //TODO: Send EMAIL
        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}

