package com.example.pizzaserviceproject.controllers;

import com.example.pizzaserviceproject.models.cafe.Cafe;
import com.example.pizzaserviceproject.models.pizza.Pizza;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {


//    public List<Pizza> getAllPizzas() {
//        List<Pizza> all = (List<Pizza>) pizzaRepository.findAll();
//        log.info(all.toString());
//        return all;
//    }
//
//
//    @GetMapping("/{id}")
//    public Pizza getPizza(@PathVariable String id) {
//        return pizzaRepository.findById(id).orElseThrow(RuntimeException::new);
//    }


//    @PostMapping("/add")
//    public ResponseEntity createPizza(@RequestBody Pizza pizza) throws URISyntaxException {
//        log.info("Added 1 pizza "+pizza.toString());
//        Cafe savedPizza = pizzaRepository.save(pizza);
//        log.info("Added pizza "+pizza.toString());
//
//        return ResponseEntity.created(new URI("/pizza/" + savedPizza.getId())).body(savedPizza);
//    }


//    @PutMapping("/{id}")
//    public ResponseEntity updatePizza(@PathVariable String id, @RequestBody Pizza pizza) {
//
//        Pizza current = pizzaRepository.findById(id).get();
//        log.info("find: "+pizza);
//        current.setName(pizza.getName());
//        current.setSize(pizza.getSize());
//        current.setDescription(pizza.getDescription());
//        current.setSpicy(pizza.getSpicy());
//        pizzaRepository.save(current);
//        return ResponseEntity.ok(current);
//
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deletePizza(@PathVariable String id) {
//        pizzaRepository.deleteById(id);
//        return ResponseEntity.ok().build();
//    }


    //****************************************************

//    // http://localhost:8080/swagger-ui/index.html
//    @GetMapping("/hello")
//    public Map<String, String> hello()
//    {
//        return Map.of("hello", "world");
//    }
//
//
//    @GetMapping("/dummy")
//    public ModelAndView renderDummyWebPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("dummy");
//        return modelAndView;
//    }



}
