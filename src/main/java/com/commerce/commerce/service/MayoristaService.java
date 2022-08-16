package com.commerce.commerce.service;

import com.commerce.commerce.entity.Mayorista;
import org.springframework.beans.support.PagedListHolder;
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
    Page<Mayorista> filter(
        String name, 
        String country, 
        String sector, 
        String sortBy,
        Pageable pageable
    );
    PagedListHolder<Mayorista> filterv2(
            String name,
            String country,
            String sector,
            String sortBy,
            Pageable pageable
    );

}
