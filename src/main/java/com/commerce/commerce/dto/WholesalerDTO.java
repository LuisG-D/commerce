package com.commerce.commerce.dto;

import lombok.Data;

@Data
public class WholesalerDTO {
    private String name;
    private String description;
    private String productType;
    private String sector;
    private String country;
    private UserDTO appUser;
}