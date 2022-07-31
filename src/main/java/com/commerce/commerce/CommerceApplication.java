package com.commerce.commerce;

import com.commerce.commerce.appuser.AppUser;
import com.commerce.commerce.appuser.AppUserRole;
import com.commerce.commerce.mayorista.domain.Mayorista;
import com.commerce.commerce.mayorista.service.MayoristaService;
import com.commerce.commerce.mayorista.service.MayoristaServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class CommerceApplication {
	private static MayoristaService service;
	public CommerceApplication(MayoristaService service) {this.service = service;}
	public static void main(String[] args) {

		SpringApplication.run(CommerceApplication.class, args);

		Mayorista mayorista = new Mayorista();
		mayorista.setAppUser(new AppUser("Mayorista2", "mayorista2@example.com", "mayorista2", AppUserRole.MAYORISTA));
		mayorista.setCountry("España");
		mayorista.setAvailable(true);
		mayorista.setDescription("Lorem Ipsum");
		mayorista.setSector("Verduda");
		mayorista.setName("MAYORISTA GRANOS");
		mayorista.setProductType("Tomate");
		service.save(mayorista);



	}

}
