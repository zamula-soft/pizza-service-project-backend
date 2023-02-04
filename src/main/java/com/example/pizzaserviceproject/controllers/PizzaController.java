package com.example.pizzaserviceproject.controllers;


import com.example.pizzaserviceproject.models.pizza.Pizza;
import com.example.pizzaserviceproject.models.pizza.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    @Autowired
    PizzaRepository pizzaRepository;

    public PizzaController() {
    }

    @GetMapping
    public Iterable<Pizza> getPizzas() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pizza> getPizzaById(
            @PathVariable(name = "id") String id) {
        return pizzaRepository.findById(id);
    }


    @DeleteMapping("/{id}")
    public void deletePizzaById(
            @PathVariable(name = "id") String id) {
        pizzaRepository.deleteById(id);
    }

    @PostMapping
    public Pizza postPizza(@RequestBody Pizza pizza) {
        if (pizza != null
                && pizza.getId() != null
                && !pizza.getId().isEmpty()
                && pizza.getName() != null
                && !pizza.getName().isEmpty())
            pizzaRepository.save(pizza);
        return pizza;

    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> putPizza(
            @PathVariable(name = "id") String id,
            @RequestBody Pizza pizza) {
        log.info("PUT " + pizza.getId() + " | " + pizza.getName());
        if (pizzaRepository.existsById(id)) {
            pizzaRepository.save(pizza);
            return new ResponseEntity<>(pizza, HttpStatus.OK);
        } else {
            pizzaRepository.save(pizza);
            return new ResponseEntity<>(pizza, HttpStatus.CREATED);
        }
    }

    @PatchMapping("/{name}")
    public List<Pizza> getPizzaByName( //optional- > list
                                       @PathVariable(name = "name") String name) {
        return pizzaRepository.findByNameContaining(name);
    }


}


