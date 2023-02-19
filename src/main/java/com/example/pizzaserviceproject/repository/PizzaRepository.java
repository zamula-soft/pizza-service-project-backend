package com.example.pizzaserviceproject.repository;

import com.example.pizzaserviceproject.entity.Pizza;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    @Transactional
    void deleteByCafeId(long cafeId);

    @Query(value = "SELECT * FROM PIZZA WHERE CAFE_ID = :cafeId", nativeQuery = true)
    public List<Pizza> findByCageId(Long cafeId);

    List<Pizza> findByNameContaining(String name);
    List<Pizza> findByDescriptionContaining(String description);

    @Query(value = "SELECT * FROM PIZZA WHERE PRICE > :priceFrom AND PRICE < :priceTo", nativeQuery = true)
    public List<Pizza> getPizzaWithPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);

    @Query(value = "SELECT * FROM PIZZA WHERE IS_SPICY = :isSpicy", nativeQuery = true)
    public List<Pizza> getPizzaWithIsSpicy(Boolean isSpicy);

    @Query("select p from Pizza p")
    public List<Pizza> getAllSorted(Sort sort);

    @Query("select p from Pizza p ORDER BY p.id")
    Page<Pizza> getPage(Pageable pageable);
}
