package com.commerce.commerce.entity;

import com.commerce.commerce.role.AppUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mayorista extends AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@JsonIgnore
	private String name;
	@JsonIgnore
	private String description;
	@JsonIgnore
	private String productType;
	@JsonIgnore
	private Boolean available;
	@JsonIgnore
	private String sector;
	@JsonIgnore
	private String country;

	public Mayorista(String username,
			String password,
			String email,
			AppUserRole appUserRole,
			String country,
			String description,
			String name,
			String productType,
			Boolean available,
			String sector) {
		super(username, password, email, appUserRole);
		this.country = country;
		this.description = description;
		this.name = name;
		this.available = available;
		this.sector = sector;
		this.productType = productType;

	}

}
