package com.example.pizzaserviceproject.models.menu;

import com.example.pizzaserviceproject.models.pizza.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, String> {
    List<Menu> findByPizzaidContaining(String pizzaid);
    List<Menu> findByCafeidContaining(String cafeid);
}
