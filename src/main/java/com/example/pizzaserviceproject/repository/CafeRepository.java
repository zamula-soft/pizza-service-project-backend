package com.example.pizzaserviceproject.repository;

import com.example.pizzaserviceproject.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
//    List<Cafe> getAllCafes();
    List<Cafe> findByNameContaining(String name);
}
