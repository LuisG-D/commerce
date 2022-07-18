package com.commerce.commerce.registration;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;




import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

@Service
@AllArgsConstructor


public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {

        return email.contains("^(?=.{1,64}@)[A-Za-z0-9\\\\+_-]+(\\\\.[A-Za-z0-9\\\\+_-]+)*@\" \n" +
                "        + \"[^-][A-Za-z0-9\\\\+-]+(\\\\.[A-Za-z0-9\\\\+-]+)*(\\\\.[A-Za-z]{2,})$");
    }
}
