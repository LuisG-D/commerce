package com.commerce.commerce.repository;
import com.commerce.commerce.entity.Mayorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MayoristaRepository extends JpaRepository<Mayorista,Long> {

    List<Mayorista> findByCountry(String country);
    List<Mayorista> findByAvailableTrue();

    List<Mayorista> findBySector(String sector);

    List<Mayorista> findByProductType(String productType);


}
