package com.example.pizzaserviceproject.controller;

import com.example.pizzaserviceproject.entity.Pizza;
import com.example.pizzaserviceproject.repository.CafeRepository;
import com.example.pizzaserviceproject.repository.PizzaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pizza")
@CrossOrigin
@Tag(name = "Pizza controller", description = "Manage CRUD REST API endpoints for pizza")
public class PizzaController {
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    // get all pizza
//    @GetMapping("/")
//    public List<Pizza> getAllPizza() {
//        List<Pizza> all = new ArrayList<>();
//        pizzaRepository.findAll().forEach(all::add);
//        log.info(all.toString());
//        return all;
//    }

    //    http://localhost:8080/pizza?cafe_id=cafeId
    @GetMapping("")
    public ResponseEntity<List<Pizza>> getAllPizzaByCafeId(
            @RequestParam(value = "cafe_id", defaultValue = "0") Long cafeId
    ) {
        log.info("cafeId = " + cafeId);
        if (cafeId == 0)
            return new ResponseEntity<>(
                    pizzaRepository.findAll(),
                    HttpStatus.OK);

        if (!cafeRepository.existsById(cafeId))
            throw new IllegalArgumentException("Cafe not found " + cafeId);



        return new ResponseEntity<>(
                pizzaRepository.findByCafeId(cafeId),
                HttpStatus.OK);

    }

    //    http://localhost:8080/pizza/sort?column=name&direction=ASC
    @GetMapping("/sort")
    public List<Pizza> sort(
            @RequestParam(name = "column", defaultValue = "id") String column,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction
    )
    {
        Sort.Direction dir = Sort.Direction.ASC;
        if (direction.equalsIgnoreCase("DESC"))
            dir = Sort.Direction.DESC;
        return pizzaRepository.getAllSorted(Sort.by(dir, column));
    }

    // http://localhost:8080/pizza/page?page=0&size=5
    @GetMapping("/page")
    public List<Pizza> pizzaPage(
          @RequestParam(name = "page", defaultValue = "0") int pageNumber,
          @RequestParam(name = "size", defaultValue = "5") int pageSize
    )
    {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return pizzaRepository
                .getPage(pageable)
                .get()
                .collect(Collectors.toList());
    }


    @GetMapping("/priceBetween")
    public List<Pizza> priceBetween (
            @RequestParam (name = "from", defaultValue = "0.0") BigDecimal priceFrom,
            @RequestParam (name = "to", defaultValue = "100.0") BigDecimal priceTo)
    {
        List<Pizza> pizzas = pizzaRepository.getPizzaWithPriceBetween(priceFrom, priceTo);
        return pizzas;
    }

    @GetMapping("/getSpicy")
    public List<Pizza> getSpicy (
            @RequestParam (name = "spicy", defaultValue = "true") Boolean isSpicy)
    {
        List<Pizza> pizzas = pizzaRepository.getPizzaWithIsSpicy(isSpicy);
        return pizzas;
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable Long id) {
        return pizzaRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    //add pizza
    //http:localhost:8080/pizza?cafe_id=cafeId
    @PostMapping
    public ResponseEntity createPizza(
            @RequestParam(value = "cafeId", defaultValue = "1") Long cafeId,
            @RequestBody Pizza pizzaRequest
    ) throws URISyntaxException {
        Pizza pizza = cafeRepository.findById(cafeId).map(
                cafe -> {
                    pizzaRequest.setCafe(cafe);
                    return pizzaRepository.save(pizzaRequest);
                }
        ).orElseThrow(
                () -> new IllegalArgumentException("Cafe not found "+ cafeId)
        );

        log.info("Added pizza " + pizza.toString());

        return ResponseEntity.created(new URI("/pizza/" + pizza.getId())).body(pizza);
    }



    @PutMapping("/{id}")
    public ResponseEntity updatePizzaById(
            @PathVariable Long id,
            @RequestBody Pizza pizza
    ) {

        Pizza current = pizzaRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Pizza with ID " + id + " not found!"));

        current.setName(pizza.getName());
        current.setCafe(pizza.getCafe());
        current.setPrice(pizza.getPrice());
        current.setSize(pizza.getSize());
        current.setDescription(pizza.getDescription());
        current.setKey_ingredients(pizza.getKey_ingredients());
        current.setIsSpicy(pizza.getIsSpicy());
        pizzaRepository.save(current);
        return ResponseEntity.ok(current);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deletePizza(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Pizza> searchPizzaByName(
            @PathParam("name") String name
    ) {
        List<Pizza> pizzaSearchResult = new ArrayList<>();
        if (name != "") {
            pizzaSearchResult = pizzaRepository.findByNameContaining(name);
            log.info("Found pizza by name" + pizzaSearchResult.toString());
        }

        return pizzaSearchResult;
    }


}
