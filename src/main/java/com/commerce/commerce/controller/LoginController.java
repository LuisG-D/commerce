package com.commerce.commerce.controller;


import com.commerce.commerce.request.LoginRequest;

import com.commerce.commerce.service.AppUserService;

import com.commerce.commerce.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.ForbiddenException;
import java.util.Objects;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/auth")
@AllArgsConstructor

public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    AuthenticationManager authenticationManager;
    RegistrationService registrationService;
    AppUserService appUserService;









    @PostMapping("/signin")
    public ResponseEntity<UserDetails> authenticateUser(@RequestBody LoginRequest loginRequest){

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (userDetails.isEnabled() && !Objects.nonNull(authentication))
                   return new ResponseEntity<>(userDetails, null, HttpStatus.OK);

            return new ResponseEntity<>( userDetails, HttpStatus.BAD_REQUEST);
           } catch (RuntimeException e) {
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email no Confirmado");
      } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos incorrectos");
            }


    }



     }
