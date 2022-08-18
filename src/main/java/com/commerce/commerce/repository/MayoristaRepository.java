package com.commerce.commerce.repository;
import com.commerce.commerce.entity.AppUser;
import com.commerce.commerce.entity.Mayorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface MayoristaRepository
        extends JpaRepository<Mayorista,Long> {

    Optional<AppUser> findByEmail(String email);

    List<Mayorista> findByCountry(String country);
    List<Mayorista> findByAvailableTrue();

    List<Mayorista> findBySector(String sector);

    List<Mayorista> findByProductType(String productType);
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1 ")
    int enableAppUser(String email);


}
