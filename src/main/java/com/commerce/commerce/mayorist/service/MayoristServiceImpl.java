package com.commerce.commerce.mayorist.service;

import com.commerce.commerce.mayorist.domain.Mayorist;
import com.commerce.commerce.mayorist.repository.MayoristRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MayoristServiceImpl implements MayoristService{

    private final Logger log = LoggerFactory.getLogger(MayoristServiceImpl.class);
    private MayoristRepository repository;

    public MayoristServiceImpl(MayoristRepository repository){this.repository = repository;}

    @Override
    public List<Mayorist> findAll() {
        log.info(("Executing findAll Mayorist"));
        return this.repository.findAll();
    }

    @Override
    public Optional<Mayorist> findById(Long id) {
        log.info("Executing findById");
        return this.repository.findById(id);
    }

    @Override
    public Long count() {
        log.info("Get the total number of mayorist");
        return this.repository.count();
    }

    @Override
    public Mayorist save(Mayorist mayorist) {
         log.info("Creating or Updating a mayorist");

         if(!this.validateMayorist(mayorist))
             return null;

         Mayorist mayoristDB = (Mayorist) this.repository.save(mayorist);

         return mayoristDB;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting mayorist by id");
        if(id == null || id < 0 || id == 0){
            log.warn("Trying to delete mayorist with wrong ID");
            return;
        }
        try{
            this.repository.deleteById(id);

        }catch (Exception e){
            log.error("Error trying delete a mayorist by ID {}",id,e);
        }

    }

    @Override
    public List<Mayorist> findByCountry(String country) {
        if(!StringUtils.hasLength(country))
            return new ArrayList<>();
        return this.repository.findByCountry(country);
    }

    private boolean validateMayorist(Mayorist mayorist){
        //if null return false
        if(mayorist == null){
            log.warn("Trying to create a null Mayorist");
            return false;
        }
        return true;
    }

    public List<Mayorist> findByAvailableTrue(){
        return this.repository.findByAvailableTrue();
    }
    public List<Mayorist> findBySector(String sector){
        return this.repository.findBySector(sector);
    }
    public List<Mayorist> findByProductType(String productType){
        return this.repository.findByProductType(productType);
    }

}
