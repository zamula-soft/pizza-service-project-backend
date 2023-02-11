package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.controllers.PizzaController;
import com.example.pizzaserviceproject.models.cafe.Cafe;
import com.example.pizzaserviceproject.models.cafe.CafeRepository;
import com.example.pizzaserviceproject.models.pizza.Pizza;
import com.example.pizzaserviceproject.models.pizza.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.sql.Time;
import java.util.Set;

@Controller
public class InitDatabaseController implements CommandLineRunner {
    //auto start after running app
    //usually used for initialization
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    private PizzaRepository pizzaRepository;
    private CafeRepository cafeRepository;

    @Autowired
    public InitDatabaseController(PizzaRepository pizzaRepository, CafeRepository cafeRepository) {
        this.pizzaRepository = pizzaRepository;
        this.cafeRepository = cafeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        https://www.just-eat.co.uk/restaurants-dominoslondonwestendlane-westhampstead/menu#itemSelectorModal-modalOpen
        log.info("Loading initial pizzas........");
        pizzaRepository.saveAll(
                Set.of(
                        new Pizza("Margaritha", "S", ""),
                        new Pizza("Bacon Double Cheese ", "M", " Bacon, ground beef, tomatoes, extra mozzarella cheese. 243kcal, 1020kJ / large slice / classic crust. from 739 kcal • from 1 serving "),
                        new Pizza("Vegi Supreme", "B", " Onions, green and red peppers, sweetcorn, mushrooms, tomatoes. 202kcal, 848kJ / large slice / classic crust. from 615 kcal • from 1 serving "),
                        new Pizza("Peperoni", "L", "Extra pepperoni and extra mozzarella cheese on a Domino’s tomato sauce base. 264kcal, 1110kJ / large slice / classic crust. from 788 kcal • from 1 serving "),
                        new Pizza("American Hot", "S", " Onions, pepperoni, green jalapeño peppers. 233kcal, 979kJ / large slice / classic crust. from 700 kcal • from 1 serving ", true),
                        new Pizza("Four cheese", "S", ""),
                        new Pizza("Meat", "S", ""),
                        new Pizza("Texas BBQ", "S", " Tangy bbq sauce topped with smoky bacon, chicken breast strips, onions, green and red peppers. 231kcal, 970kJ / large slice / classic crust. from 727 kcal • from 1 serving "),
                        new Pizza("Burger Pizza", "S", "")
                )
        );

        log.info("Loading initial cafes........");

        cafeRepository.saveAll(
                Set.of(
                        new Cafe(1, "Vienna Pizza Shop", "Vienna", "pizza-stop@pizza.com", "+48 50 500 500 50", new Time(8, 30, 00), new Time(22,00, 00)),
                        new Cafe(2, "Samurai Pizza Club", "Tokyo", "samurai-pizza@pizza.com", "+11 12 222 222 22", new Time(06, 00, 00), new Time(21,15, 00)),
                        new Cafe(3, "Cowboy Time", "Los Angeles", "cowboy-time@pizza.com", "+11 10 000 000 00", new Time(10, 00, 00), new Time(23,00, 00)),
                        new Cafe(2, "Just Pizza", "Chicago", "just-pizza@pizza.com", "+11 11 010 010 01", new Time(10, 00, 00), new Time(23,00, 00))
                        )
        );

        log.info("Loading initial menu........");
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
