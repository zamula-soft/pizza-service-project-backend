package com.example.pizzaserviceproject.repository;

import com.example.pizzaserviceproject.entity.Cafe;
import com.example.pizzaserviceproject.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
//    List<Cafe> getAllCafes();
    List<Pizza> findByNameContaining(String name);
    List<Pizza> findByDescriptionContaining(String description);

    @Query(value = "SELECT * FROM PIZZA WHERE PRICE > :priceFrom AND PRICE < :priceTo", nativeQuery = true)
    public List<Pizza> getPizzaWithPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);
}
