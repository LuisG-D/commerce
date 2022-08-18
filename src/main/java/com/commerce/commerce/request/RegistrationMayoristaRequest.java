package com.commerce.commerce.request;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RegistrationMayoristaRequest extends RegistrationRequest{

     private final String country;
     private final String description;
     private final String name;
     private final String productType;
     private final String sector;
    private final Boolean available;

    public RegistrationMayoristaRequest(
                                        String country,
                                        String description,
                                        String name,
                                        String productType,
                                        String sector,
                                        Boolean available) {
        super();
        this.country = country;
        this.description = description;
        this.name = name;
        this.productType = productType;
        this.sector = sector;
        this.available = available;
    }
}
