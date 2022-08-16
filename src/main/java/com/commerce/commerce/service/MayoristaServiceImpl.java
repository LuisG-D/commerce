package com.commerce.commerce.service;
import com.commerce.commerce.entity.Mayorista;
import com.commerce.commerce.repository.MayoristaRepository;
import com.commerce.commerce.repository.MayoristaFilterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MayoristaServiceImpl implements MayoristaService{

    private final Logger log = LoggerFactory.getLogger(MayoristaServiceImpl.class);
    private MayoristaRepository repository;
    private MayoristaFilterRepository repositoryFilter;
    public MayoristaServiceImpl(){}

    public MayoristaServiceImpl(MayoristaRepository repository, MayoristaFilterRepository repositoryFilter){
        this.repository = repository;
        this.repositoryFilter = repositoryFilter;
    }

    @Override
    public List<Mayorista> findAll() {
        log.info(("Executing findAll Mayorist"));
        return this.repository.findAll();
    }

    @Override
    public Page<Mayorista> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Optional<Mayorista> findById(Long id) {
        log.info("Executing findById");
        return this.repository.findById(id);
    }

    @Override
    public Long count() {
        log.info("Get the total number of mayorista");
        return this.repository.count();
    }

    @Override
    public Mayorista save(Mayorista mayorista) {
        log.info("Creating or Updating a mayorista");

        if(!this.validateMayorist(mayorista))
            return null;

        Mayorista mayoristaDB = (Mayorista) this.repository.save(mayorista);

        return mayoristaDB;
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
    public List<Mayorista> findByCountry(String country) {
        return this.repository.findByCountry(country);
    }

    private boolean validateMayorist(Mayorista mayorista){
        //if null return false
        if(mayorista == null){
            log.warn("Trying to create a null Mayorista");
            return false;
        }
        return true;
    }

    public List<Mayorista> findByAvailableTrue(){
        return this.repository.findByAvailableTrue();
    }
    public List<Mayorista> findBySector(String sector){
        return this.repository.findBySector(sector);
    }
    public List<Mayorista> findByProductType(String productType){
        return this.repository.findByProductType(productType);
    }

    @Override
    public Page<Mayorista> filter(
        String name, 
        String country, 
        String sector,
        String sortBy,
        Pageable pageable
    ) {
        
        return this.repositoryFilter.getFilterPaginate(
            name, 
            country, 
            sector, 
            sortBy,
            pageable
        );
    }
    @Override
    public PagedListHolder<Mayorista> filterv2(
            String name,
            String country,
            String sector,
            String sortBy,
            Pageable pageable
    ){
        return this.repositoryFilter.getFilterPaginatev2(
                name,
                country,
                sector,
                sortBy,
                pageable
        );


    }



}