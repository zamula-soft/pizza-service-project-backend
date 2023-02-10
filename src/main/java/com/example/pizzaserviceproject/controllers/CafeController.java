package com.example.pizzaserviceproject.controllers;


import com.example.pizzaserviceproject.models.cafe.Cafe;
import com.example.pizzaserviceproject.models.cafe.CafeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cafe")
@Api(value = "Pizza API", protocols = "http")
public class CafeController {
    private static final Logger log = LoggerFactory.getLogger(CafeController.class);

    @Autowired
    CafeRepository cafeRepository;

    public CafeController() {
    }

    @ApiOperation(value = "Get list of all cafes in database", response = Model.class, code = 200)
    @GetMapping
    public String getAllCafes(Model model) { //model lets bind instance into template
        Iterable<Cafe> all = cafeRepository.findAll();
        log.info(all.toString());
        model.addAttribute("cafes", all);
        log.info(all.toString());
        return "cafe/list-cafe";
    }

    //    Search
    @GetMapping("/search")
    public String searchCafe(@PathParam("name") String name, Model model) {
        List<Cafe> cafeByName = cafeRepository.findByNameContaining(name);
        model.addAttribute("cafes", cafeByName);
        log.info(cafeByName.toString());
        return "cafe/list-cafe";

    }

    //    Add
    @GetMapping("/add")
    public String addCafe(Cafe cafe) {
        return "cafe/add-cafe";
    }


    @PostMapping
    public String addNewCafe(
            @Valid Cafe cafe,
            BindingResult result,
            Model model){
        if (result.hasErrors())
        {
            return "cafe/add-cafe";
        }
        cafeRepository.save(cafe);
        return "redirect:/cafe";
    }


    //Delete
    @GetMapping("/delete/{id}")
    public String deleteCafeById(@PathVariable(name = "id") String id){
        log.info("Deleting cafe id: "+id);
        cafeRepository.deleteById(id);
        return "redirect:/cafe";
    }


    //Edit
    @GetMapping("/edit/{id}")
    public String updateCafe(@PathVariable(name = "id") String id, Model model){
        log.info("Editing cafe id: "+id);
        Cafe cafe = cafeRepository.findById(id).get();
        log.info("find: "+cafe);
        model.addAttribute("cafe", cafe);
        return "cafe/update-cafe";
    }

    @PostMapping("/update/{id}")
    public String updateCafeModel(@PathVariable(name = "id") String id,
                                    @Valid Cafe cafe, //validate
                                    BindingResult result,
                                    Model model)
    {//result of validation
        log.info(cafe.toString());
        if (result.hasErrors())
        {
            cafe.setId(id);
            return "cafe/update-cafe";
        }
        cafeRepository.save(cafe);
        return "redirect:/cafe";
    }


    

}


