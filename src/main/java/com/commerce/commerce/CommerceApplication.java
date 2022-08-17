package com.commerce.commerce;

import com.commerce.commerce.entity.AppUser;
import com.commerce.commerce.entity.Mayorista;
import com.commerce.commerce.repository.MayoristaRepository;
import com.commerce.commerce.role.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.mappers.ModelMapper;

@SpringBootApplication

public class CommerceApplication {

	/*@Autowired
	private static MayoristaRepository service;
	public CommerceApplication(MayoristaRepository service){
		this.service = service;
	}*/


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(CommerceApplication.class, args);


			/*Mayorista mayorista = new Mayorista(request.getCountry(), request.getDescription(), request.getName(), request.getProducType(), request.getAvailable(), request.getSector());

			mayorista.setAppUser(new AppUser("MayoristaCafe","gonzaleis@gmail.com","admin", AppUserRole.MAYORISTA));
			mayorista.setCountry("Ecuador");
			mayorista.setAvailable(true);
			mayorista.setDescription("Lorem Ipsum");
			mayorista.setSector("Granos");
			mayorista.setName("MAYORISTA CAFE ECUADOR");
			mayorista.setProductType("Cafe");
			service.save(mayorista);*/
		}






	}


