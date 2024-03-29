package com.commerce.commerce.controller;


import com.commerce.commerce.appuser.AppUser;
import com.commerce.commerce.registration.LoginRequest;
import com.commerce.commerce.registration.LoginService;
import com.commerce.commerce.registration.RegistrationService;
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
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/auth")
@AllArgsConstructor

public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    AuthenticationManager authenticationManager;
    RegistrationService registrationService;
    LoginService loginService;




    @GetMapping("/hello")
    public String hello() {
        log.info("Executing hello world method from logger");
        // diferentes niveles de logger:
        // log.warn("Executing hello world method from logger");
        // log.error("Executing hello world method from logger");
        return "Hola mundo";
    }
    @PostMapping("/signin")
    public ResponseEntity<UserDetails> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, AppUser user) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if(Objects.nonNull(authentication)){
                return new ResponseEntity<>(userDetails,null,HttpStatus.OK);
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de usuario incorrectos");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de usuario incorrectos");
        }

    }



}