package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.entity.Cafe;
import com.example.pizzaserviceproject.entity.Pizza;
import com.example.pizzaserviceproject.repository.CafeRepository;
import com.example.pizzaserviceproject.repository.PizzaRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest {

    @Autowired
    CafeRepository cafeRepository;
    @Autowired
    PizzaRepository pizzaRepository;

    @Test
    public void testA_CafeCreation() throws Exception{
        Cafe cafe = new Cafe();
        cafe.setName("Test cafe name");
        cafe.setEmail("test@email.com");

        Cafe savedCafe = cafeRepository.save(cafe);

        assertEquals(Optional.ofNullable(savedCafe.getId()), Optional.of(1L));
        assertEquals(savedCafe.getName(), "Test cafe name");
        assertEquals(savedCafe.getEmail(), "test@email.com");
    }

    @Test
    public void testB_PizzaCreation() throws Exception{
        Pizza pizza = new Pizza();
        Cafe cafe = cafeRepository.findById(1L).get();
        pizza.setCafe(cafe);
        pizza.setName("Test pizza name");
        pizza.setSize("L");

        Pizza savedPizza = pizzaRepository.save(pizza);

        assertEquals(Optional.ofNullable(savedPizza.getId()), Optional.of(1L));
        assertEquals(savedPizza.getName(), "Test pizza name");
        assertEquals(savedPizza.getSize(), "L");
    }

    @Test
    public void testC_UpdateCafeDetails() throws Exception{
        Cafe cafe = cafeRepository.findById(1L).get();
        cafe.setName("Updated test cafe name");
        cafe.setEmail("new_test@email.com");

        Cafe saved = cafeRepository.save(cafe);

        assertEquals(Optional.ofNullable(saved.getId()), Optional.of(1L));
        assertEquals(saved.getName(), "Updated test cafe name");
        assertEquals(saved.getEmail(), "new_test@email.com");
    }

    public void testD_UpdatePizzaDetails() throws Exception{
        Pizza pizza = pizzaRepository.findById(1L).get();
        pizza.setName("Updated test pizza name");
        pizza.setSize("XL");

        Pizza saved = pizzaRepository.save(pizza);

        assertEquals(Optional.ofNullable(saved.getId()), Optional.of(1L));
        assertEquals(saved.getName(), "Updated test pizza name");
        assertEquals(saved.getSize(), "XL");
    }


}
