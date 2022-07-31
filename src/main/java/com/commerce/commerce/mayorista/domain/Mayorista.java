package com.commerce.commerce.mayorista.domain;


import com.commerce.commerce.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            nullable = false,
            name = "app_user_mayorista")
    private AppUser appUser;
    public Mayorista() {}

}
