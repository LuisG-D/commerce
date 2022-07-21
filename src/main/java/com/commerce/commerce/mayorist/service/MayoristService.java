package com.commerce.commerce.mayorist.service;

import com.commerce.commerce.mayorist.domain.Mayorist;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface MayoristService {
    //Spring repository methods

    List<Mayorist> findAll();

    Optional<Mayorist> findById(Long id);

    Long count();
    Mayorist save(Mayorist mayorist);

    void deleteById(Long id);

    //Custom Methods

    List<Mayorist> findByCountry(String country);
}
