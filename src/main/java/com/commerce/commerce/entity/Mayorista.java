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
public class Mayorista  extends AppUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    //@Column(nullable = false)
    @JsonIgnore
    private String name;
   // @Column(nullable = false)
   @JsonIgnore
    private String description;
  //  @Column(nullable = false)
  @JsonIgnore
    private String productType;
   // @Column(nullable = false)
   @JsonIgnore
    private Boolean available;
   // @Column(nullable = false)
   @JsonIgnore
    private String sector;
   // @Column(nullable = false)
    @JsonIgnore
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
