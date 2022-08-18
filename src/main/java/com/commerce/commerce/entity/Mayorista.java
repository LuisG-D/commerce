package com.commerce.commerce.entity;


import com.commerce.commerce.role.AppUserRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mayorista  extends AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String productType;
    @Column(nullable = false)
    private Boolean available;
    @Column(nullable = false)
    private String sector;
    @Column(nullable = false)
    private String country;

    /*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "app_user_mayorista")
    private AppUser appUser;*/
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
        super(username,password,email,appUserRole);
        this.country = country;
        this.description = description;
        this.name = name;
        this.available = available;
        this.sector = sector;
        this.productType = productType;

    }


}
