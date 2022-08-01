package com.commerce.commerce.mayorista.repository;
import com.commerce.commerce.mayorista.domain.Mayorista;
import com.commerce.commerce.mayorista.service.MayoristaService;
import net.bytebuddy.TypeCache.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface MayoristaRepository extends JpaRepository<Mayorista,Long> {

    List<Mayorista> findByCountry(String country);
    List<Mayorista> findByAvailableTrue();

    List<Mayorista> findBySector(String sector);

    List<Mayorista> findByProductType(String productType);


}
