package com.commerce.commerce.registration;


import com.commerce.commerce.appuser.AppUser;
import com.commerce.commerce.security.jwt.JwtResponse;
import com.commerce.commerce.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/auth")
@AllArgsConstructor

public class AuthController {
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    AuthenticationManager authenticationManager;
    RegistrationService registrationService;
    LoginService loginService;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/hello")
    public String hello() {
        log.info("Executing hello world method from logger");
        // diferentes niveles de logger:
        // log.warn("Executing hello world method from logger");
        // log.error("Executing hello world method from logger");
        return "Hola mundo";
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUser user = (AppUser) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roles));
    }

    @GetMapping(path="dentro")
    public String dentro(@RequestParam("sing") String sing){
        return loginService.singinUser(sing);


    }
    @PostMapping("/signup")
    public String register(@RequestBody RegistrationRequest request){

        return registrationService.register(request);
    }
    @GetMapping(path ="confirm")
    public String confirm(@RequestParam("token")
                          String token){
        return registrationService.confirmToken(token);
    }

}
