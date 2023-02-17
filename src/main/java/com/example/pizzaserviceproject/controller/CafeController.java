package com.example.pizzaserviceproject.controller;

import com.example.pizzaserviceproject.entity.Cafe;
import com.example.pizzaserviceproject.repository.CafeRepository;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cafe")
@CrossOrigin
public class CafeController {
    private static final Logger log = LoggerFactory.getLogger(CafeController.class);

    private CafeRepository cafeRepository;

    @Autowired
    public CafeController(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @GetMapping
    public List<Cafe> getAll() {
        List<Cafe> all = new ArrayList<>();
        cafeRepository.findAll().forEach(all::add);
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
    public Cafe getCafe(@PathVariable Long id) {
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
    public ResponseEntity updateCafe(@PathVariable Long id, @RequestBody Cafe cafe) {

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
    public ResponseEntity deleteClient(@PathVariable Long id) {
        cafeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    

}


