package com.commerce.commerce.registration;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

        private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){

        return registrationService.register(request);
    }
    @GetMapping(path ="confirm")
    public String confirm(@RequestParam("token")
                          String token){
        return registrationService.confirmToken(token);
    }
}
