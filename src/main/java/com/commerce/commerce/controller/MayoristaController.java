package com.commerce.commerce.controller;

import com.commerce.commerce.mayorista.domain.Mayorista;
import com.commerce.commerce.mayorista.dto.CountDTO;
import com.commerce.commerce.mayorista.repository.MayoristaRepository;
import com.commerce.commerce.mayorista.service.MayoristaService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/mayoristas")
public class MayoristaController {
    private final Logger log = LoggerFactory.getLogger(MayoristaController.class);

    //Dependencia
    private MayoristaService mayoristaService;
    private MayoristaRepository repository;
    EntityManager em;

    public MayoristaController(MayoristaService mayoristaService)
    {
        this.mayoristaService = mayoristaService;
    }

    /*           Spring CRUD Methods                       */

    //Buscamos a mayoristas por ID
    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mayorista> finById(@PathVariable Long id){
        log.info("REST resquest to find one Mayorista");

        Optional<Mayorista> mayoristaOpt = this.mayoristaService.findById(id);

        //Vemos si existe o no
        return mayoristaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Listamos a los mayoritas

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)

    public Page<Mayorista> findAll(@RequestParam(name="page", defaultValue = "0")int page, Mayorista mayorista){
        Pageable pageRequest = PageRequest.of(page,2);
        Page<Mayorista> mayoristas = this.mayoristaService.findAll(pageRequest);

        return mayoristas;
    }



    //Crear un mayorista
    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mayorista> create(@RequestBody Mayorista mayorista){
        log.info("REST request to create mayorista");
        //Comprobamos si ya existe un mayoristaa con el ID
        if(mayorista.getId() != null){
            log.warn("Trying to create mayorista with existent ID");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.mayoristaService.save(mayorista));
    }

   //Update a mayorista
   @PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Mayorista> update(@RequestBody Mayorista mayorista){
        log.info("REST request to update and existing mayorista");
        if(mayorista.getId() == null){ //NO HAY ID, POR LO TANTO NO SE PROCEDE A ACTUALIZAR
            log.warn("Trying to update an existing mayorista without id");
            return ResponseEntity.badRequest().build();
            }
        return ResponseEntity.ok(this.mayoristaService.save(mayorista));

   }
   //Delete a Mayorist
    @DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mayorista> delete(@PathVariable Long id){
        log.info("REST request to DELETE an existing mayorista");

        this.mayoristaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Count all mayorista on the DDBB
    @GetMapping(value="/count",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountDTO> count(){
        log.info("REST request to count all mayorista");
        Long count = this.mayoristaService.count();
        CountDTO dto = new CountDTO(count);
        dto.setMessage("This is the List of All Mayoristas");
        return ResponseEntity.ok(dto);
    }

/**
 * ====================TODO CUSTOM CRUD METHODS ==========
 **/
    @GetMapping(path = "/filter")    
    public ResponseEntity<Map<String, Object>> filter(
        @RequestParam(required = false) String sector,
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String name,
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "15") int itemPerPage,
        @RequestParam(defaultValue = "0") int page
    ){
        log.info("REST request filter mayorista");
        try {
            Pageable pageable = PageRequest.of(page, itemPerPage);
            List<Mayorista> mayoristas = new ArrayList<Mayorista>();

            Page<Mayorista> pageMayoristas = this.mayoristaService.filter(
                name, country, sector, sortBy, pageable);

            mayoristas = pageMayoristas.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("mayoristas", mayoristas);
            response.put("currentPage", pageMayoristas.getNumber());
            response.put("totalItems", pageMayoristas.getTotalElements());
            response.put("totalPages", pageMayoristas.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
