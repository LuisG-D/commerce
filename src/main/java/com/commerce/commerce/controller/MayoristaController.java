package com.commerce.commerce.controller;

import com.commerce.commerce.mayorista.domain.Mayorista;
import com.commerce.commerce.mayorista.dto.CountDTO;
import com.commerce.commerce.mayorista.repository.MayoristaRepository;
import com.commerce.commerce.mayorista.service.MayoristaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class MayoristaController {
    private final Logger log = LoggerFactory.getLogger(MayoristaController.class);

    //Dependencia
    private MayoristaService mayoristaService;
    private MayoristaRepository repository;

    public MayoristaController(MayoristaService mayoristaService) {this.mayoristaService = mayoristaService;}

    /*           Spring CRUD Methods                       */

    //Buscamos a mayoristas por ID
    @GetMapping("/mayoristas/{id}")
    public ResponseEntity<Mayorista> finById(@PathVariable Long id){
        log.info("REST resquest to find one Mayorista");

        Optional<Mayorista> mayoristaOpt = this.mayoristaService.findById(id);

        //Vemos si existe o no
        return mayoristaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Listamos a los mayoritas
    @GetMapping("/mayoristas")
    public List<Mayorista> findAll(){
        return this.mayoristaService.findAll();
    }

    //Crear un mayorista
    @PostMapping("/mayoristas")
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
   @PutMapping("mayoristas/{id}")
   public ResponseEntity<Mayorista> update(@RequestBody Mayorista mayorista){
        log.info("REST request to update and existing mayorista");
        if(mayorista.getId() == null){ //NO HAY ID, POR LO TANTO NO SE PROCEDE A ACTUALIZAR
            log.warn("Trying to update an existing mayorista without id");
            return ResponseEntity.badRequest().build();
            }
        return ResponseEntity.ok(this.mayoristaService.save(mayorista));

   }
   //Delete a Mayorist
    @DeleteMapping("mayoristas/{id}")
    public ResponseEntity<Mayorista> delete(@PathVariable Long id){
        log.info("REST request to DELETE an existing mayorista");

        this.mayoristaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Count all mayorista on the DDBB
    @GetMapping("mayoristas/count")
    public ResponseEntity<CountDTO> count(){
        log.info("REST request to count all mayorista");
        Long count = this.mayoristaService.count();
        CountDTO dto = new CountDTO(count);
        dto.setMessage("This is the List of All Mayoristas");
        return ResponseEntity.ok(dto);
    }

/** ====================TODO CUSTOM CRUD METHODS ==========**/

@GetMapping("mayorista/{country}")
    public List<Mayorista>findByCountry(@PathVariable String country){
    return this.mayoristaService.findByCountry(country);
}



}
