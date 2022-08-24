package com.commerce.commerce.controller;
import com.commerce.commerce.dto.CountDTO;
import com.commerce.commerce.dto.WholesalerDTO;
import com.commerce.commerce.entity.Mayorista;
import com.commerce.commerce.repository.MayoristaRepository;
import com.commerce.commerce.request.RegistrationMayoristaRequest;
import com.commerce.commerce.service.MayoristaService;
import com.commerce.commerce.service.RegistrationMayoristaService;
import com.commerce.commerce.service.RegistrationService;
import io.swagger.annotations.ApiModelProperty;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth/mayoristas")
public class MayoristaController {
    private final Logger log = LoggerFactory.getLogger(MayoristaController.class);

    //Dependencia
    private MayoristaService mayoristaService;
    private MayoristaRepository repository;
    private RegistrationService registrationService;
    EntityManager em;
    private RegistrationMayoristaService registrationMayoristaService;

    @Autowired
    private ModelMapper modelMapper;


    public MayoristaController(MayoristaService mayoristaService,
                               MayoristaRepository repository,
                               RegistrationMayoristaService registrationMayoristaService)
    {
        super();
        this.mayoristaService = mayoristaService;
        this.repository = repository;
        this.registrationMayoristaService = registrationMayoristaService;

    }

    /*           Spring CRUD Methods                       */

    //Buscamos a mayoristas por ID
    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mayorista> finById(@PathVariable Long id){
        log.info("REST resquest to find one Mayorista");

        Optional<Mayorista> mayoristaOpt = this.repository.findById(id);

        //Vemos si existe o no
        return mayoristaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Listamos a los mayoritas

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)

    public Page<Mayorista> findAll(@RequestParam(name="page", defaultValue = "0")int page, Mayorista mayorista){
        Pageable pageRequest = PageRequest.of(page,2);
        Page<Mayorista> mayoristas = this.repository.findAll(pageRequest);

        return mayoristas;
    }



    //Crear un mayorista
    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody RegistrationMayoristaRequest registrationMayoristaRequest){
        return registrationMayoristaService.register(registrationMayoristaRequest);
    }
    
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationMayoristaService.confirmToken(token);
    }


    //Update a mayorista
    @PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mayorista> update(@RequestBody Mayorista mayorista){
        log.info("REST request to update and existing mayorista");
        if(mayorista.getId() == null){ //NO HAY ID, POR LO TANTO NO SE PROCEDE A ACTUALIZAR
            log.warn("Trying to update an existing mayorista without id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.repository.save(mayorista));

    }
    //Delete a Mayorist
    @DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
    @ApiModelProperty(value = "Aqui Buscamos por country")
    @GetMapping(value ="", params ={"country"})
    public List<Mayorista>findByCountry(@RequestParam String country){
        return this.mayoristaService.findByCountry(country);
    }


    @GetMapping(value ="", params ={"sector"})
    @ApiModelProperty(value = "Aqui Buscamos por sector")
    public List<Mayorista>findBySector(@RequestParam String sector){
        return this.mayoristaService.findBySector(sector);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<Map<String, Object>> filter(
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "3") int itemPerPage,
            @RequestParam(defaultValue = "0") int page
    ){
        log.info("REST request filter mayorista");
        log.info(Integer.toString(itemPerPage));
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

    @GetMapping(path = "/filter2")
    public ResponseEntity<Map<String, Object>> filterv2(
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "3") int itemPerPage,
            @RequestParam(defaultValue = "0") int page
    ){
        Pageable pageable = PageRequest.of(page, itemPerPage);

        PagedListHolder<Mayorista> plMayorista = this.mayoristaService.filterv2(
                name, country, sector, sortBy , pageable);

        List<Mayorista> mayoristas = plMayorista.getPageList();
        List<WholesalerDTO> wholesalerDTOs =  mayoristas
                .stream()
                .map(mayorista->modelMapper.map(mayorista, WholesalerDTO.class)).collect(Collectors.toList());

        boolean isNextFinal =   plMayorista.getPage() != plMayorista.getLastLinkedPage();
        boolean isPreviousInitial =   plMayorista.getPage() != plMayorista.getFirstLinkedPage();
        Map<String, Object> response = new HashMap<>();
        response.put("mayoristas", wholesalerDTOs);
        response.put("currentPage", plMayorista.getPage());
        response.put("total", plMayorista.getNrOfElements());
        response.put("totalItems", plMayorista.getPageSize());
        response.put("totalPages", plMayorista.getPageCount());
        response.put("isNextFinal", isNextFinal);
        response.put("isPreviousInitial", isPreviousInitial);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
