package com.example.pizzaserviceproject.controllers;


import com.example.pizzaserviceproject.models.cafe.Cafe;
import com.example.pizzaserviceproject.models.cafe.CafeRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/cafe")
@CrossOrigin
public class CafeController {
    private static final Logger log = LoggerFactory.getLogger(CafeController.class);

    @Autowired
    CafeRepository cafeRepository;

    public CafeController() {
    }

    @GetMapping
    public List<Cafe> getAllCafes() {
        List<Cafe> all = (List<Cafe>) cafeRepository.findAll();
        log.info(all.toString());
        return all;
    }


    @GetMapping("/search")
    public List<Cafe> searchCafe(@PathParam("name") String name) {
        List<Cafe> cafeByName = cafeRepository.findByNameContaining(name);
        log.info(cafeByName.toString());
        return cafeByName;

    }


    @GetMapping("/{id}")
    public Cafe getCafe(@PathVariable String id) {
        return cafeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/add")
    public ResponseEntity createCafe(@RequestBody Cafe cafe) throws URISyntaxException {
        log.info("Added 1 cafe "+cafe.toString());
        Cafe savedCafe = cafeRepository.save(cafe);
        log.info("Added cafe "+cafe.toString());

        return ResponseEntity.created(new URI("/cafe/" + savedCafe.getId())).body(savedCafe);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCafe(@PathVariable String id, @RequestBody Cafe cafe) {

        Cafe currentCafe = cafeRepository.findById(id).get();
        log.info("find: "+cafe);
        currentCafe.setRating(cafe.getRating());
        currentCafe.setName(cafe.getName());
        currentCafe.setCity(cafe.getCity());
        currentCafe.setCountry(cafe.getCountry());
        currentCafe.setEmail(cafe.getEmail());
        currentCafe.setSite(cafe.getSite());
        currentCafe.setFacebook(cafe.getFacebook());
        currentCafe.setDescription(cafe.getDescription());
        cafeRepository.save(currentCafe);
        return ResponseEntity.ok(currentCafe);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable String id) {
        cafeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    

}


