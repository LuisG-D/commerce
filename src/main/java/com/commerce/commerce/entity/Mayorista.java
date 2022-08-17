package com.commerce.commerce.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mayorista {
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            nullable = false,
            name = "app_user_mayorista")
    private AppUser appUser;
    public Mayorista(String country,
                     String description,
                     String name,
                     String producType,
                     Boolean available,
                     String sector) {}


}
