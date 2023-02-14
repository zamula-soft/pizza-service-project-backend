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
                        new Pizza("Margaritha", "S", "The most famous pizza..."),
                        new Pizza("Bacon Double Cheese ", "M", " Bacon, ground beef, tomatoes, extra mozzarella cheese. 243kcal, 1020kJ / large slice / classic crust. from 739 kcal • from 1 serving "),
                        new Pizza("Vegi Supreme", "B", " Onions, green and red peppers, sweetcorn, mushrooms, tomatoes. 202kcal, 848kJ / large slice / classic crust. from 615 kcal • from 1 serving "),
                        new Pizza("Peperoni", "L", "Extra pepperoni and extra mozzarella cheese on a Domino’s tomato sauce base. 264kcal, 1110kJ / large slice / classic crust. from 788 kcal • from 1 serving "),
                        new Pizza("American Hot", "S", " Onions, pepperoni, green jalapeño peppers. 233kcal, 979kJ / large slice / classic crust. from 700 kcal • from 1 serving ", true),
                        new Pizza("Four cheese", "S", "Four sorts of cheese..."),
                        new Pizza("Meat", "S", "Meat pizza... eat pizza..."),
                        new Pizza("Texas BBQ", "S", " Tangy bbq sauce topped with smoky bacon, chicken breast strips, onions, green and red peppers. 231kcal, 970kJ / large slice / classic crust. from 727 kcal • from 1 serving "),
                        new Pizza("Burger Pizza", "S", "Pizza and burger at the same time - it is possible...")
                )
        );

        log.info("Loading initial cafes........");

        cafeRepository.saveAll(
                Set.of(
                        new Cafe(1, "Masanielli – Francesco Martucci", "Caserta", "Italy",
                                "",
                                "",
                                "http://www.pizzeriaimasanielli.it/",
                                "https://www.facebook.com/masaniellifrancescomartucci",
                                "+0823 154 0786",
                                false,
                                "napoletano",
                                "Francesco Martucci continues his personal evolution in his gastronomic initiative applied to pizza, an internationally popular dish...",
                                new Time(8, 30, 00), new Time(22,00, 00)),
                        new Cafe(3, "Peppe Pizzeria", "Paris", "France",
                                "2 Place Saint Blaise, Reservation, 75020 Paris",
                                "",
                                "http://www.peppeparis.fr/it/",
                                "https://www.facebook.com/paris.peppe/",
                                "+33 1 45 35 59 13",
                                false,
                                "napoletano",
                                "The energy of Peppe Cutraro transpires here, in the style as well as in the welcoming Italian hospitality, friendliness, and attentiveness...",
                                new Time(8, 30, 00), new Time(22,00, 00)),
                        new Cafe(2, "Tony’s Pizza Napoletana", "San Francisco", "USA",
                                "1570 Stockton St, San Francisco, CA 94133",
                                "",
                                "http://tonyspizzanapoletana.com/",
                                "https://www.facebook.com/tonyspizza415",
                                "+1 415-835-9888",
                                true,
                                "napoletano",
                                "This is a traditional venue for high-quality pizza located right in the heart of San Francisco...",
                                new Time(9, 30, 00), new Time(22,00, 00)),
                        new Cafe(5, "Sartoria Panatieri", "Barselona", "Spain",
                                "Carrer de Provença, 330, 08037 Barcelona, Spagna",
                                "",
                                "http://www.sartoriapanatieri.com/",
                                "https://www.facebook.com/sartoriapanatieri/",
                                "+34 931 37 63 85",
                                true,
                                "napoletano",
                                "The pizzas use a dough made from local organic flours...",
                                new Time(9, 00, 00), new Time(23,00, 00)),
                        new Cafe(4, "SPizzeria Peppe – Napoli sta’ ca”", "Tokyo", "Japan",
                                "Giappone, 〒106-0041 Tokyo, Minato City, Azabudai, 1",
                                "",
                                "http://peppenapolistaca.com/",
                                "https://www.facebook.com/peppenapolistaca/",
                                "+81 3-6459-1846",
                                true,
                                "napoletano",
                                "The pizzas use a dough made from local organic flours...",
                                new Time(7, 00, 00), new Time(21,00, 00))



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
