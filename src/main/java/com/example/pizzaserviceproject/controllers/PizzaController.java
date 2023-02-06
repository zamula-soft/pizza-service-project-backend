package com.example.pizzaserviceproject.controllers;


import com.example.pizzaserviceproject.models.pizza.Pizza;
import com.example.pizzaserviceproject.models.pizza.PizzaRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    @Autowired
    PizzaRepository pizzaRepository;

    public PizzaController() {
    }

    @GetMapping
    public String getAllPizzas(Model model) { //model lets bind instance into template
        Iterable<Pizza> all = pizzaRepository.findAll();
        log.info(all.toString());
        model.addAttribute("pizzas", all);
        log.info(all.toString());
        return "pizza/list-of-pizzas";
    }

    //    Search
    @GetMapping("/search")
    public String searchPizzas(@PathParam("name") String name, Model model) {
        List<Pizza> pizzaByName = pizzaRepository.findByNameContaining(name);
        model.addAttribute("pizzas", pizzaByName);
        log.info(pizzaByName.toString());
        return "pizza/list-of-pizzas";

    }

    //    Add
    @GetMapping("/add")
    public String addPizza(Pizza pizza) {
        return "pizza/add-pizza";
    }


    @PostMapping
    public String addNewPizza(
            @Valid Pizza pizza,
            BindingResult result,
            Model model){
        if (result.hasErrors())
        {
            return "pizza/add-pizza";
        }
        pizzaRepository.save(pizza);
        return "redirect:/pizzas";
    }

}


