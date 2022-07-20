package com.commerce.commerce.mayorist.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "mayorist")
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Mayorist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="mayorists")
    private String name;
    private String description;
    private String productType;
    private Boolean available;
    private String sector;
    private String country;
    public Mayorist() {}

}
