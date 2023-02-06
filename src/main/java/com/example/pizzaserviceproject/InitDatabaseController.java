package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.controllers.PizzaController;
import com.example.pizzaserviceproject.models.pizza.Pizza;
import com.example.pizzaserviceproject.models.pizza.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class InitDatabaseController implements CommandLineRunner {
    //auto start after running app
    //usually used for initialization
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    private PizzaRepository pizzaRepository;

    @Autowired
    public InitDatabaseController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        https://www.just-eat.co.uk/restaurants-dominoslondonwestendlane-westhampstead/menu#itemSelectorModal-modalOpen
        log.info("Loading initial pizzas........");
        pizzaRepository.saveAll(
                Set.of(
                        new Pizza("Margaritha", "S", ""),
                        new Pizza("Bacon Double Cheese ", "M", " Bacon, ground beef, tomatoes, extra mozzarella cheese. 243kcal, 1020kJ / large slice / classic crust. from 739 kcal • from 1 serving "),
                        new Pizza("Margaritha", "B", ""),
                        new Pizza("Peperoni", "L", "Extra pepperoni and extra mozzarella cheese on a Domino’s tomato sauce base. 264kcal, 1110kJ / large slice / classic crust. from 788 kcal • from 1 serving "),
                        new Pizza("Mexican", "S", ""),
                        new Pizza("Four cheese", "S", ""),
                        new Pizza("Meat", "S", ""),
                        new Pizza("Texas BBQ", "S", " Tangy bbq sauce topped with smoky bacon, chicken breast strips, onions, green and red peppers. 231kcal, 970kJ / large slice / classic crust. from 727 kcal • from 1 serving "),
                        new Pizza("Burger Pizza", "S", "")
                )
        );

        log.info("Loading initial cafes........");
//        pizzaRepository.saveAll(
//                Set.of(
//                        new Pizza("Margaritha", "S", ""),
//                        new Pizza("Peperoni", "S", ""),
//                        new Pizza("Mexican", "S", ""),
//                        new Pizza("Four cheese", "S", "")
//                )
//        );

        log.info("Loading initial menu........");
//        pizzaRepository.saveAll(
//                Set.of(
//                        new Pizza("Margaritha", "S", ""),
//                        new Pizza("Peperoni", "S", ""),
//                        new Pizza("Mexican", "S", ""),
//                        new Pizza("Four cheese", "S", "")
//                )
//        );

        log.info("Loading initial sales........");
//        pizzaRepository.saveAll(
//                Set.of(
//                        new Pizza("Margaritha", "S", ""),
//                        new Pizza("Peperoni", "S", ""),
//                        new Pizza("Mexican", "S", ""),
//                        new Pizza("Four cheese", "S", "")
//                )
//        );

        log.info("Loading initial warehouse........");
//        pizzaRepository.saveAll(
//                Set.of(
//                        new Pizza("Margaritha", "S", ""),
//                        new Pizza("Peperoni", "S", ""),
//                        new Pizza("Mexican", "S", ""),
//                        new Pizza("Four cheese", "S", "")
//                )
//        );


    }
}
