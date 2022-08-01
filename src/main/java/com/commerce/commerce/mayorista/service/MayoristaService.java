package com.commerce.commerce.mayorista.service;

import com.commerce.commerce.mayorista.domain.Mayorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface MayoristaService {
    //Spring repository methods

    List<Mayorista> findAll();
    Page<Mayorista> findAll(Pageable pageable);

    Optional<Mayorista> findById(Long id);

    Long count();

    Mayorista save(Mayorista mayorista);

    void deleteById(Long id);

    //Custom Methods

    List<Mayorista> findByCountry(String country);
    List<Mayorista> findBySector(String sector);

}
