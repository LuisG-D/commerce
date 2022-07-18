package com.commerce.commerce.registration;


import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;




import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

@Service
@AllArgsConstructor


public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {
        if(email.contains("@") && email.contains(".")){
            return true;
        }else{

            throw new IllegalStateException("Invalid email: " + email);

        }




    }
}