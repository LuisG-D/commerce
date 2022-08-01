package com.commerce.commerce;

import com.commerce.commerce.appuser.AppUser;
import com.commerce.commerce.appuser.AppUserRole;
import com.commerce.commerce.mayorista.domain.Mayorista;
import com.commerce.commerce.mayorista.service.MayoristaService;
import com.commerce.commerce.registration.RegistrationRequest;
import com.commerce.commerce.registration.RegistrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class CommerceApplication {
	//private static MayoristaService service;
	//public CommerceApplication(MayoristaService service) {this.service = service;}

	public static void main(String[] args) {

		SpringApplication.run(CommerceApplication.class, args);
/*
		Mayorista mayorista = new Mayorista();

		mayorista.setAppUser(new AppUser("MayoristaCafe","gonzalezdenisluis@gmail.com","admin", AppUserRole.MAYORISTA));
		mayorista.setCountry("Ecuador");
		mayorista.setAvailable(true);
		mayorista.setDescription("Lorem Ipsum");
		mayorista.setSector("Granos");
		mayorista.setName("MAYORISTA CAFE ECUADOR");
		mayorista.setProductType("Cafe");
		service.save(mayorista);*/



	}

}
