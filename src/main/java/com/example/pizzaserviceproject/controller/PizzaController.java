package com.example.pizzaserviceproject.controller;

import com.example.pizzaserviceproject.entity.Cafe;
import com.example.pizzaserviceproject.entity.Pizza;
import com.example.pizzaserviceproject.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pizza")
@CrossOrigin
public class PizzaController {
    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    // add pizza
    @GetMapping
    public List<Pizza> getAll() {
        List<Pizza> all = new ArrayList<>();
        pizzaRepository.findAll().forEach(all::add);
        log.info(all.toString());
        return all;
    }

    @GetMapping("/priceBetween")
    public List<Pizza> priceBetween (
            @RequestParam (name = "from", defaultValue = "0.0") BigDecimal priceFrom,
            @RequestParam (name = "to", defaultValue = "100.0") BigDecimal priceTo)
    {
        List<Pizza> pizzas = pizzaRepository.getPizzaWithPriceBetween(priceFrom, priceTo);
        return pizzas;
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable Long id) {
        return pizzaRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    @PostMapping("/add")
    public ResponseEntity createPizza(@RequestBody Pizza pizza) throws URISyntaxException {
        log.info("Added 1 pizza "+pizza.toString());
        Pizza savedPizza = pizzaRepository.save(pizza);
        log.info("Added pizza "+pizza.toString());

        return ResponseEntity.created(new URI("/pizza/" + savedPizza.getId())).body(savedPizza);
    }


    @PutMapping("/{id}")
    public ResponseEntity updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {

        Pizza current = pizzaRepository.findById(id).get();
        log.info("find: "+pizza);
        current.setName(pizza.getName());
        current.setSize(pizza.getSize());
        current.setDescription(pizza.getDescription());
        current.setIsSpicy(pizza.getIsSpicy());
        pizzaRepository.save(current);
        return ResponseEntity.ok(current);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deletePizza(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }




//    @GetMapping
//    public String getAllPizzas(Model model) { //model lets bind instance into template
//        Iterable<Pizza> all = pizzaRepository.findAll();
//        log.info(all.toString());
//        model.addAttribute("pizzas", all);
//        log.info(all.toString());
//        return "pizza/list-pizza";
//    }

//    //    Search
//    @GetMapping("/search")
//    public String searchPizzas(@PathParam("name") String name, Model model) {
//        List<Pizza> pizzaByName = pizzaRepository.findByNameContaining(name);
//        List<Pizza> pizzaByDescription = pizzaRepository.findByDescriptionContaining(name);
//        List<Pizza> resultList = new ArrayList<Pizza>();
//        resultList.addAll(pizzaByName);
//        resultList.addAll(pizzaByDescription);
//        model.addAttribute("pizzas", resultList);
//        log.info(pizzaByName.toString());
//        return "pizza/list-pizza";
//
//    }
//
//    //    Add
//    @GetMapping("/add")
//    public String addPizza(Pizza pizza) {
//        return "pizza/add-pizza";
//    }
//
//
//    @PostMapping
//    public String addNewPizza(
//            @Valid Pizza pizza,
//            BindingResult result,
//            Model model){
//        if (result.hasErrors())
//        {
//            return "pizza/add-pizza";
//        }
//        pizzaRepository.save(pizza);
//        return "redirect:/pizza";
//    }
//
//
//    //Delete
//    @GetMapping("/delete/{id}")
//    public String deletePizzaById(@PathVariable(name = "id") String id){
//        log.info("Deleting pizza id: "+id);
//        pizzaRepository.deleteById(id);
//        return "redirect:/pizza";
//    }
//
//
//    //Edit
//    @GetMapping("/edit/{id}")
//    public String updatePizza(@PathVariable(name = "id") String id, Model model){
//        log.info("Editing pizza id: "+id);
//        Pizza pizza = pizzaRepository.findById(id).get();
//        log.info("find: "+pizza);
//        model.addAttribute("pizza", pizza);
//        return "pizza/update-pizza";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updatePizzaModel(@PathVariable(name = "id") String id,
//                                    @Valid Pizza pizza, //validate
//                                    BindingResult result,
//                                    Model model)
//    {//result of validation
//        log.info(pizza.toString());
//        if (result.hasErrors())
//        {
//            pizza.setId(id);
//            return "pizza/update-pizza";
//        }
//        pizzaRepository.save(pizza);
//        return "redirect:/pizza";
//    }

}
