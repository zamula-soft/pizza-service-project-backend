package com.example.pizzaserviceproject.models.pizza;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, String> {
    List<Pizza> findByNameContaining(String name);
}
