package com.example.pizzaserviceproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class TestController {


    // http://localhost:8080/swagger-ui/index.html
    @GetMapping("/hello")
    public Map<String, String> hello()
    {
        return Map.of("hello", "world");
    }


    @GetMapping("/dummy")
    public ModelAndView renderDummyWebPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dummy");
        return modelAndView;
    }

}
