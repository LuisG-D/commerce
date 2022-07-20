package com.commerce.commerce.mayorist.repository;

import com.commerce.commerce.appuser.AppUser;
import com.commerce.commerce.mayorist.domain.Mayorist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MayoristRepository extends JpaRepository<Mayorist,Long> {
    List<Mayorist> findByCountry(String country);
    List<Mayorist> findByAvailableTrue();

    List<Mayorist> findBySector(String sector);

    List<Mayorist> findByProductType(String productType);





}
