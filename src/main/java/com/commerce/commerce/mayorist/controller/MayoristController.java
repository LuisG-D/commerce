package com.commerce.commerce.mayorist.controller;

import com.commerce.commerce.mayorist.domain.Mayorist;
import com.commerce.commerce.mayorist.dto.CountDTO;
import com.commerce.commerce.mayorist.repository.MayoristRepository;
import com.commerce.commerce.mayorist.service.MayoristService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class MayoristController {
    private final Logger log = LoggerFactory.getLogger(MayoristController.class);

    //Dependencia
    private final MayoristService mayoristService;
    private MayoristRepository repository;

    public MayoristController(MayoristService mayoristService) {this.mayoristService = mayoristService;}

    /*           Spring CRUD Methods                       */

    //Buscamos a mayoristas por ID
    @GetMapping("/mayorists/{id}")
    public ResponseEntity<Mayorist> finById(@PathVariable Long id){
        log.info("REST resquest to find one Mayorist");

        Optional<Mayorist> mayoristOpt = this.mayoristService.findById(id);

        //Vemos si existe o no
        return mayoristOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Listamos a los mayoritas
    @GetMapping("/mayorists")

    public List<Mayorist> findAll(){
    return this.mayoristService.findAll();
        }

    //Crear un mayorista
    @PostMapping("/mayorists")
    public ResponseEntity<Mayorist> create(@RequestBody Mayorist mayorist){
        log.info("REST request to create mayorist");
        //Comprobamos si ya existe un mayorista con el ID
        if(mayorist.getId() != null){
            log.warn("Trying to create mayorist with existent ID");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.mayoristService.save(mayorist));
    }

   //Update a mayorist
   @PutMapping("/mayorists")
   public ResponseEntity<Mayorist> update(@RequestBody Mayorist mayorist){
        log.info("REST request to update and existing mayorist");
        if(mayorist.getId() == null){ //NO HAY ID, POR LO TANTO SE PROCEDE A ACTUALIZAR
            log.warn("Trying to update an existing mayorist without id");
            return ResponseEntity.badRequest().build();
            }
        return ResponseEntity.ok(this.mayoristService.save(mayorist));

   }
   //Delete a Mayorist
    @DeleteMapping("/mayorists/{id}")
    public ResponseEntity<Mayorist> delete(@PathVariable Long id){
        log.info("REST request to DELETE an existing mayorist");

        this.mayoristService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Count all mayorist on the DDBB
    @GetMapping("/mayorists/count")
    public ResponseEntity<CountDTO> count(){
        log.info("REST request to count all mayorist");
        Long count = this.mayoristService.count();
        CountDTO dto = new CountDTO(count);
        dto.setMessage("Have a good day!");
        return ResponseEntity.ok(dto);
    }

/** ====================TODO CUSTOM CRUD METHODS ==========**/
@Query("SELECT * FROM mayorist t WHERE LOWER(t.country) LIKE LOWER(CONCAT('%', ?1,'%'))")
@GetMapping("/mayoristfilter")
    public List<Mayorist>findByCountry(@PathVariable String country){
    return this.mayoristService.findByCountry(country);
}



}
