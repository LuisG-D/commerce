package com.commerce.commerce.mayorist.repository;

import com.commerce.commerce.mayorist.domain.Mayorist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MayoristRepository extends JpaRepository<Mayorist,Long> {
    List<Mayorist> findByCountryAndProductType(String country, String productType);
    List<Mayorist> findByAvailableTrue();

    List<Mayorist> findBySector(String sector);
    List<Mayorist> findByProductType(String productType);

    List<Mayorist> findByCountry(String country);
}
