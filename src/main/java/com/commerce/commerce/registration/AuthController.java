package com.commerce.commerce.registration;


import com.commerce.commerce.appuser.AppUserRepository;
import com.commerce.commerce.registration.token.ConfirmationTokenRepository;

import com.commerce.commerce.security.payload.LoginRequest;


import org.springframework.security.authentication.AuthenticationManager;


import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "/**", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "api/v1")


public class AuthController {

        private final AuthenticationManager authManager;

        private RegistrationService registrationService;
        private final AppUserRepository appUserRepository;
    private final ConfirmationTokenRepository confirmationToken;

    public AuthController(AuthenticationManager authManager,
                          RegistrationService registrationService,
                          AppUserRepository appUserRepository,
                          ConfirmationTokenRepository confirmationToken) {
        this.authManager = authManager;
        this.registrationService = registrationService;
        this.appUserRepository = appUserRepository;
        this.confirmationToken = confirmationToken;
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {


        return "Login";
    }


    @PostMapping ("/registration")
    public String register(@RequestBody RegistrationRequest request){

        return registrationService.register(request);
    }
    @GetMapping(path ="confirm")
    public String confirm(@RequestParam("token")
                          String token){
        return registrationService.confirmToken(token);
    }
}
