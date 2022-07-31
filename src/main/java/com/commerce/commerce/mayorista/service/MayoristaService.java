package com.commerce.commerce.mayorista.service;

import com.commerce.commerce.mayorista.domain.Mayorista;

import java.util.List;
import java.util.Optional;

public interface MayoristaService {
    //Spring repository methods

    List<Mayorista> findAll();

    Optional<Mayorista> findById(Long id);

    Long count();

    Mayorista save(Mayorista mayorista);

    void deleteById(Long id);

    //Custom Methods

    List<Mayorista> findByCountry(String country);
}
